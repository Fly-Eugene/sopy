package com.ssafy.sopy.controller;

import com.ssafy.sopy.dto.BookFileReqDto;
import com.ssafy.sopy.dto.BookDto;
import com.ssafy.sopy.dto.BookReqDto;
import com.ssafy.sopy.dto.BookSearchReqDto;
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


}
