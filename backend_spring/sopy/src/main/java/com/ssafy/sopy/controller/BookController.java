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

    // make audio
    @PostMapping("/audio/{bookId}")
    public Object makeAudio(@PathVariable("bookId") Long bookId, BookFileReqDto params) throws IOException {
        return bookService.makeAudio(params, bookId);
    }

    @GetMapping("/main")
    public Object getBookList() {
        return bookService.getBookList();
    }

    @GetMapping("/search")
    public Object searchBook(@RequestBody BookSearchReqDto params) {
        System.out.println(params);
        return bookService.searchBook(params.getTitle());
    }

    @GetMapping("/detail")
    public BookDto bookDetail(@RequestParam Long bookId) {
        return bookService.getBookDetail(bookId);
    }


}
