package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.repository.BookRepository;
import com.ssafy.sopy.dto.BookReqDto;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final FilesService filesService;
    private final ImageService imageService;

    public BookService(BookRepository bookRepository, FilesService filesService, ImageService imageService) {
        this.bookRepository = bookRepository;
        this.filesService = filesService;
        this.imageService = imageService;
    }

    public Object makeBook(BookReqDto params) {
        Book book = bookRepository.save(Book.builder().id(params.getId()).genre(params.getGenre()).introduce(params.getIntroduce()).title(params.getTitle()).build());
        filesService.
        return null;
    }
}
