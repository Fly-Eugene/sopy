package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.*;
import com.ssafy.sopy.domain.repository.BookRepository;
import com.ssafy.sopy.domain.repository.UserLikeRepository;
import com.ssafy.sopy.domain.repository.UserRepository;
import com.ssafy.sopy.dto.*;
import com.ssafy.sopy.util.HttpURLConnectionUtil;
import com.ssafy.sopy.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final UserLikeRepository userLikeRepository;
    private final FilesService filesService;
    private final ImageService imageService;
    private final HttpURLConnectionUtil httpURLConnectionUtil;
    private final String djangoURL;

    public BookService(BookRepository bookRepository, UserRepository userRepository, UserLikeRepository userLikeRepository, FilesService filesService, ImageService imageService, HttpURLConnectionUtil httpURLConnectionUtil,
                       @Value("${djangoURL}") String djangoURL) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.userLikeRepository = userLikeRepository;
        this.filesService = filesService;
        this.imageService = imageService;
        this.httpURLConnectionUtil = httpURLConnectionUtil;
        this.djangoURL = djangoURL;
    }

    @Transactional
    public Object makeBook(BookReqDto params) throws IOException {
        BookImage bookImage = imageService.makeBookImage(params.getImageFile());
        Book book = bookRepository.save(Book.builder()
                .id(params.getId()).genre(params.getGenre())
                .introduce(params.getIntroduce()).title(params.getTitle())
                .author(params.getAuthor()).translator(params.getTranslator())
                .publisher(params.getPublisher()).publishedDate(params.getPublishedDate())
                .bookImage(bookImage)
                .build());
        return book.entityToDto();
    }

    @Transactional
    public Object makeAudio(BookAudioReqDto params, Long bookId) throws IOException {
        Book book = bookRepository.getById(bookId);
        Map<String, String> jsonData = new HashMap<String, String>();
        String textPath = null;
        if(params.getImageFile().getSize() > 0){
            Files imageFile = filesService.makeFiles(new ArrayList<>(Arrays.asList(params.getImageFile())), book).get(0);
            jsonData.put("path", imageFile.getPath());
            jsonData.put("name", imageFile.getOrgName());
            httpURLConnectionUtil.post(djangoURL + "book/ocr/", jsonData);
        }
        if(params.getTextFile().getSize() > 0){
            Files textFile = filesService.makeFiles(new ArrayList<>(Arrays.asList(params.getTextFile())), book).get(0);
            textPath = textFile.getPath() + textFile.getSysName();
        } else{
//            textPath =
        }
        // TTS 요청 후 audio파일 DB에 저장
//        PathNode pathNode = pathSplit(textPath);
//        jsonData.put("path", pathNode.path);
//        jsonData.put("name", pathNode.name);
//        httpURLConnectionUtil.post(djangoURL + "/book/tts", jsonData);
        return null;
    }
    public PathNode pathSplit(String fullPath) {
        int index = fullPath.lastIndexOf("/");
        if (index != -1) {
            return new PathNode(fullPath.substring(0, index+1), fullPath.substring(index+1));
        }
        return new PathNode("", "");
    }

    public Object getBookList() {
        List<Book> books = bookRepository.getBooks();
        List<BookDto> results = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (Book book : books) {
            results.add(book.entityToDto());
        }

        map.put("books", results);
        return map;
    }

    public Object searchBook(String title) {
        List<Book> searchBookList = bookRepository.searchBook(title);

        Map<String, Object> map = new HashMap<>();
        List<BookDto> results = new ArrayList<>();

        // bookId만 가져오도록 수정하면 좋을 것 같다.
        for (Book book : searchBookList) {
            results.add(getBookDetail(book.getId()));
        }

        map.put("books", results);
        return map;
    }

    public BookDto getBookDetail(Long bookId) {
        // 책 정보 얻어오기
        Book book = bookRepository.getById(bookId);
        // 프로필 얻어오기
        BookImage getImage = bookRepository.getBookImage(bookId);
        Image image = getImage == null ? null : BookImage.builder()
                .id(getImage.getId())
                .imageName(getImage.getImageName())
                .path(getImage.getPath())
                .imageOrgName(getImage.getImageOrgName())
                .thumbnail(getImage.getThumbnail())
                .build();


        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .introduce(book.getIntroduce())
                .genre(book.getGenre())
                .author(book.getAuthor())
                .translator(book.getTranslator())
                .publisher(book.getPublisher())
                .publishedDate(book.getPublishedDate())
                .bookImage(image == null ? null : image.entityToDto())
                .build();

    }

    @Transactional
    public Object genreFilter(String genre) {
        List<Book> bookList = bookRepository.genreFilter(genre);
        Map<String, Object> map = new HashMap<>();
        List<BookDto> results = new ArrayList<>();

        for (Book book : bookList) {
            results.add(book.entityToDto());
        }

        map.put("books", results);
        return map;
    }

    @Transactional
    public Object bookLike(LikeReqDto params) {
        Book book = bookRepository.getById(params.getBookId());
        UserLike userLike = userLikeRepository.save(UserLike.builder()
                .user(userRepository.getById(params.getUserId() == null ? getUserId() : params.getUserId()))
                .book(book)
                .build());

        return UserLikeDto.builder()
                .id(userLike.getId())
                .user(userLike.getUser().entityToDto())
                .book(userLike.getBook().entityToDto())
                .build();
    }

    @Transactional
    public Object likeCancel(LikeReqDto params) {
        Long userId = params.getUserId() == null ? getUserId() : params.getUserId();
        Long bookId = params.getBookId();
        return userLikeRepository.cancel(userId, bookId);
    }

    public Object getLikeList() {
        List<UserLike> userLikeList = userLikeRepository.getLikeBooks(getUserId());
        Map<String, Object> map = new HashMap<>();
        List<BookDto> results = new ArrayList<>();

        for (UserLike userLike : userLikeList) {
            Long bookId = userLike.getId();
            Book book = bookRepository.getById(bookId);
            results.add(book.entityToDto());
        }
        map.put("book", results);

        return map;
    }


    private Long getUserId() {
        String s = SecurityUtil.getCurrentUsername().get();
        return userRepository.findByEmail(s).getId();
    }



    class PathNode {
        String path, name;
        public PathNode() {
        }
        public PathNode(String path, String name) {
            this.path = path;
            this.name = name;
        }
    }
}
