package com.ssafy.sopy.controller;

import com.ssafy.sopy.dto.BookFileReqDto;
import com.ssafy.sopy.dto.BookDto;
import com.ssafy.sopy.dto.BookReqDto;
import com.ssafy.sopy.dto.BookSearchReqDto;
import com.ssafy.sopy.service.BookService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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

    // responseEntity controller단으로 모으는게 깔끔할듯
    @GetMapping("/audio/{bookId}")
    public ResponseEntity<Resource> makeAudio(@PathVariable("bookId") Long bookId, @RequestParam Integer bookPage) throws IOException {
        FileSystemResource resource = new FileSystemResource(bookService.getAudio(bookId, bookPage));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mp3"))
                .body(resource);
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


}
