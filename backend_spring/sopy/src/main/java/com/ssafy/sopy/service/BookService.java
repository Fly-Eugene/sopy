package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.BookImage;
import com.ssafy.sopy.domain.entity.Files;
import com.ssafy.sopy.domain.repository.BookImageRepository;
import com.ssafy.sopy.domain.repository.BookRepository;
import com.ssafy.sopy.dto.BookFileReqDto;
import com.ssafy.sopy.dto.BookDto;
import com.ssafy.sopy.dto.BookReqDto;
import com.ssafy.sopy.util.FileUtil;
import com.ssafy.sopy.util.HttpURLConnectionUtil;
import com.ssafy.sopy.util.PdfUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final FilesService filesService;
    private final ImageService imageService;
    private final HttpURLConnectionUtil httpURLConnectionUtil;
    private final String djangoURL;
    private final PdfUtil pdfUtil;
    private final FileUtil fileUtil;

    public BookService(BookRepository bookRepository, FilesService filesService, ImageService imageService, HttpURLConnectionUtil httpURLConnectionUtil,
                       @Value("${djangoURL}") String djangoURL, PdfUtil pdfUtil, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.filesService = filesService;
        this.imageService = imageService;
        this.httpURLConnectionUtil = httpURLConnectionUtil;
        this.djangoURL = djangoURL;
        this.pdfUtil = pdfUtil;
        this.fileUtil = fileUtil;
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
        // db 저장
        filesService.saveDir(resultDir, book);
        // Dir_path book에 저장
        System.out.println("book = " + book);
        bookRepository.save(Book.builder().id(book.getId()).author(book.getAuthor()).genre(book.getGenre()).introduce(book.getIntroduce())
                .publishedDate(book.getPublishedDate()).publisher(book.getPublisher()).title(book.getTitle())
                .translator(book.getTranslator()).bookImage(book.getBookImage()).dirPath(resultDir.getParent()).build());
        // 장고 쪽으로 ocr 요청
        Map<String, String>jsonData = new HashMap<>();
        jsonData.put("path", resultDir.getParent());
        httpURLConnectionUtil.post(djangoURL + "book/ocr/", jsonData);

        // text 파일들 DB에 저장
        File textDir = new File(book.getDirPath() + "/" + "text");
        if(!textDir.exists()) textDir.mkdirs();
        filesService.saveDir(textDir, book);
        return textDir;
    }
    @Transactional
    public Object makeAudio(Long bookId) throws IOException {
        Book book = bookRepository.getById(bookId);

        // tts
        Map<String, String>jsonData = new HashMap<>();
        jsonData.put("path", book.getDirPath());
        httpURLConnectionUtil.post(djangoURL + "book/tts/", jsonData);

        File soundDir = new File(book.getDirPath() + "/" + "sound");
        System.out.println("soundDir.getPath() = " + soundDir.getPath());
        if(!soundDir.exists()) soundDir.mkdirs();
        filesService.saveDir(soundDir, book);
        return soundDir;
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

    @Transactional
    public Object getBookList() {
        List<Book> books = bookRepository.getBooks();
        List<BookDto> results = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (Book book : books) {
            results.add(BookDto.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .introduce(book.getIntroduce())
                    .genre(book.getGenre())
                    .author(book.getAuthor())
                    .translator(book.getTranslator())
                    .publisher(book.getPublisher())
                    .publishedDate(book.getPublishedDate())
                    .build());
        }

        map.put("books", results);
        return map;
    }

    @Transactional
    public Object searchBook(String title) {
        List<Book> searchBookList = bookRepository.searchBook(title);

        Map<String, Object> map = new HashMap<>();
        List<BookDto> results = new ArrayList<>();

        for (Book book : searchBookList) {
            results.add(BookDto.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .introduce(book.getIntroduce())
                    .genre(book.getGenre())
                    .author(book.getAuthor())
                    .translator(book.getTranslator())
                    .publisher(book.getPublisher())
                    .publishedDate(book.getPublishedDate())
                    .build());
        }

        map.put("books", results);
        return map;
    }

    @Transactional
    public BookDto getBookDetail(Long bookId) {
        Book book = bookRepository.getById(bookId);

        // profile
        // profile builder

        // book + profile ??????? XXXXXX?????

        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .introduce(book.getIntroduce())
                .genre(book.getGenre())
                .author(book.getAuthor())
                .translator(book.getTranslator())
                .publisher(book.getPublisher())
                .publishedDate(book.getPublishedDate())
                .build();
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
