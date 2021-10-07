package com.ssafy.sopy.controller;
import com.ssafy.sopy.dto.*;
import com.ssafy.sopy.service.BookService;
import com.ssafy.sopy.service.BookmarkService;
import com.ssafy.sopy.service.UserBookmarkService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final BookmarkService bookmarkService;
    private final UserBookmarkService userBookmarkService;

    public BookController(BookService bookService, BookmarkService bookmarkService, UserBookmarkService userBookmarkService) {
        this.bookService = bookService;
        this.bookmarkService = bookmarkService;
        this.userBookmarkService = userBookmarkService;
    }

    @PostMapping
    public Object makeBook(BookReqDto params) throws IOException {
        return bookService.makeBook(params);
    }

    // make text(text 편집? => 귀찮으니 나중에 생각)
    @PostMapping("/text/{bookId}")
    public Object makeText(@PathVariable("bookId") Long bookId, BookFileReqDto params) throws IOException {
        return bookService.makeText(params, bookId);
    }

    // make audio, text 파일로 받는 경우 없다네유
    @PostMapping("/audio/{bookId}")
    public Object makeAudio(@PathVariable("bookId") Long bookId) throws IOException {
        return bookService.makeAudio(bookId);
    }

    // responseEntity controller단으로 모으는게 깔끔할듯
    @GetMapping("/audio/{bookId}")
    public Object getAudio(@PathVariable("bookId") Long bookId, @RequestParam Integer bookPage) throws IOException {
        return bookService.getS3File(bookId, bookPage, "/sound", ".wav");
    }
    @GetMapping("/text/{bookId}")
    public Object getText(@PathVariable("bookId") Long bookId, @RequestParam Integer bookPage) throws IOException {
        return bookService.getTextFile(bookId, bookPage, "/txt",".txt");
    }

    @GetMapping("/main")
    public Object getBookList() {
        return bookService.getBookList();
    }

    @PostMapping("/search")
    public Object searchBook(@RequestBody BookSearchReqDto params) {
        return bookService.searchBook(params.getTitle());
    }

    @GetMapping("/genre")
    public Object genreFilter(@RequestParam String genre) {
        return bookService.genreFilter(genre);
    }

    @GetMapping("/detail")
    public BookDto bookDetail(@RequestParam Long bookId) {
        return bookService.getBookDetail(bookId);
    }

    @PostMapping("/like")
    public Object bookLike(@RequestBody LikeReqDto params) {
        return bookService.bookLike(params);
    }

    @DeleteMapping("/like")
    public Object likeCancel(@RequestBody LikeReqDto params) {
        return bookService.likeCancel(params);
    }

    @GetMapping("/like")
    public Object likeList() {
        return bookService.getLikeList();
    }

    @GetMapping("/readlist")
    public List<BookDto> getReadBookList(){
        return bookmarkService.getReadBookList();
    }
    // s3 관련 controller
    @PostMapping("/api/v1/upload")
    public String uploadImage(MultipartFile file) {
        return bookService.uploadImage(file);
    }

    @PostMapping("/bookmark")
    public Object setBookmark(@RequestBody UserBookmarkDto params){
        System.out.println("params = " + params);
        return userBookmarkService.setUserBookmark(params);
    }
    @GetMapping("/bookmark/{bookId}")
    public Object getBookmark(@PathVariable("bookId") Long bookId){
        return userBookmarkService.getUserBookmark(bookId);
    }
    @DeleteMapping("/bookmark/{bookId}")
    public void deleteBookmark(@PathVariable("bookId") Long bookId){
        userBookmarkService.deleteUserBookmark(bookId);
    }
}
