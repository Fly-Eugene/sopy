package com.ssafy.sopy.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.BookImage;
import com.ssafy.sopy.domain.entity.Image;
import com.ssafy.sopy.domain.entity.UserLike;
import com.ssafy.sopy.domain.repository.BookRepository;
import com.ssafy.sopy.domain.repository.UserLikeRepository;
import com.ssafy.sopy.domain.repository.UserRepository;
import com.ssafy.sopy.dto.*;
import com.ssafy.sopy.util.FileUtil;
import com.ssafy.sopy.util.HttpURLConnectionUtil;
import com.ssafy.sopy.util.PdfUtil;
import com.ssafy.sopy.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final UserLikeRepository userLikeRepository;
    private final FilesService filesService;
    private final ImageService imageService;
    private final BookmarkService bookmarkService;
    private final HttpURLConnectionUtil httpURLConnectionUtil;
    private final String djangoURL;
    private final PdfUtil pdfUtil;
    private final FileUtil fileUtil;
    // S3 관련 service
    private final UploadService s3Service;


    public BookService(BookRepository bookRepository, UserRepository userRepository, UserLikeRepository userLikeRepository, FilesService filesService, ImageService imageService, HttpURLConnectionUtil httpURLConnectionUtil,
                       @Value("${djangoURL}") String djangoURL, PdfUtil pdfUtil, FileUtil fileUtil, UploadService s3Service, BookmarkService bookmarkService) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.userLikeRepository = userLikeRepository;
        this.filesService = filesService;
        this.imageService = imageService;
        this.httpURLConnectionUtil = httpURLConnectionUtil;
        this.djangoURL = djangoURL;
        this.pdfUtil = pdfUtil;
        this.fileUtil = fileUtil;
        this.bookmarkService = bookmarkService;
        // s3 관련 service
        this.s3Service = s3Service;
    }

    @Transactional
    public BookDto makeBook(BookReqDto params) throws IOException {
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
    public Object makeText(BookFileReqDto params, Long bookId) throws IOException {
        // 이미지 파일 저장
        Book book = bookRepository.getById(bookId);
        // pdf면 image로 바꿔 저장, image면 그냥 저장
        File resultDir = null;
        if(params.getPdfFile() != null){            // pdf
            resultDir = pdfUtil.pdfToImg(params.getPdfFile());
        } else {                                    // image
            resultDir = fileUtil.saveImages(params.getImageFiles());
        }
        Integer pageSize = resultDir.listFiles().length;
        // db 저장
        filesService.saveDir(resultDir, book);
        // Dir_path book에 저장
        System.out.println("book = " + book);
        bookRepository.save(Book.builder().id(book.getId()).author(book.getAuthor()).genre(book.getGenre()).introduce(book.getIntroduce())
                .publishedDate(book.getPublishedDate()).publisher(book.getPublisher()).title(book.getTitle())
                .translator(book.getTranslator()).bookImage(book.getBookImage()).dirPath(resultDir.getParent()).pageSize(pageSize).build());

        // 장고 쪽으로 ocr 요청
        Map<String, String>jsonData = new HashMap<>();
        jsonData.put("path", resultDir.getParent());
        jsonData.put("pageSize", pageSize.toString());
        httpURLConnectionUtil.post(djangoURL + "book/ocr/", jsonData);

//         text 파일들 DB에 저장
        File textDir = new File(book.getDirPath() + "/" + "text");
        if(!textDir.exists()) textDir.mkdirs();
        filesService.saveDir(textDir, book);
//        return resultDir.getParent();
        return book.entityToDto();
    }

    @Transactional
    public Object makeAudio(Long bookId) throws IOException {
        Book book = bookRepository.getById(bookId);

        // tts
        Map<String, String>jsonData = new HashMap<>();
        jsonData.put("path", book.getDirPath());
        jsonData.put("pageSize", book.getPageSize().toString());
        httpURLConnectionUtil.post(djangoURL + "book/tts/", jsonData);
        httpURLConnectionUtil.post(djangoURL + "book/tts2/", jsonData);

//        File soundDir = new File(book.getDirPath() + "/" + "sound");
//        System.out.println("soundDir.getPath() = " + soundDir.getPath());
//        if(!soundDir.exists()) soundDir.mkdirs();
//        filesService.saveDir(soundDir, book);
        return book.getDirPath();
        /* 후에 text파일로도 받는 경우 생기면 이거 이용하면 됨
        // text 파일이 parameter에 없음 => 이미 저장되어 있는 상태
        if (params.getTextFiles().size() == 0 || params.getTextFiles().get(0).getSize() == 0){

        // parameter에 text 파일이 있으면 저장 안되어 있는 상태
        } else {

        }
        */
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
            Long bookId = userLike.getBook().getId();
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

    @Transactional(readOnly = false)
    public String getS3File(Long bookId, Integer bookPage, String dir, String type) {
        Book book = bookRepository.getById(bookId);
        bookmarkService.setBookmark(book, bookPage);
        return s3Service.getFileUrl(book.getDirPath() + dir, bookPage.toString() + type).replace("%5C", "/");
    }

    @Transactional(readOnly = false)
    public Object getTextFile(Long bookId, Integer bookPage, String dir, String type) throws IOException {
        Book book = bookRepository.getById(bookId);
        bookmarkService.setBookmark(book, bookPage);
        String directory = (book.getDirPath() + dir).replace("\\", "/");
        String fileName = bookPage.toString() + type;
        System.out.println("directory = " + directory + " " + fileName);
//        directory = "c:/sopy/upload/2021/09/30/0bb7127f-489b-4539-8045-769e62621ece/txt";
        return s3Service.getText(directory, fileName);
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

    // ========================= s3 확인 ================================================
    public String uploadImage(MultipartFile file) {
        String fileName = UUID.randomUUID().toString().concat(getFileExtension(file.getOriginalFilename()));
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        System.out.println("file = " + file.getSize());
        try (InputStream inputStream = file.getInputStream()) {
            s3Service.uploadFile("", fileName, inputStream, objectMetadata);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생했습니다 (%s)", file.getOriginalFilename()));
        }
        return s3Service.getFileUrl(fileName);
    }

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 (%s) 입니다"));
        }
    }

}
