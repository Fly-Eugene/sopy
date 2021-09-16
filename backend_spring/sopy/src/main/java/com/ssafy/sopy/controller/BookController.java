package com.ssafy.sopy.controller;

import com.ssafy.sopy.dto.BookAudioReqDto;
import com.ssafy.sopy.dto.BookReqDto;
import com.ssafy.sopy.service.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
