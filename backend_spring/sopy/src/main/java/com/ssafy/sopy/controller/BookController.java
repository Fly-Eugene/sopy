package com.ssafy.sopy.controller;

import com.ssafy.sopy.domain.repository.BookRepository;
import com.ssafy.sopy.dto.*;
import com.ssafy.sopy.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Object makeBook(BookReqDto params) throws IOException {
        return bookService.makeBook(params);
    }

    @PostMapping("/audio/{book_id}")
    public Object makeAudio(@PathVariable("book_id") Long bookId, BookAudioReqDto params) throws IOException {
        return bookService.makeAudio(params, bookId);
    }

    @GetMapping("/main")
    public Object getBookList() {
        return bookService.getBookList();
    }

    @GetMapping("/search")
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
}
