package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.repository.BookRepository;
import com.ssafy.sopy.dto.BookReqDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

    @Transactional
    public Object makeBook(BookReqDto params) throws IOException {
        Book book = bookRepository.save(Book.builder().id(params.getId()).genre(params.getGenre()).introduce(params.getIntroduce()).title(params.getTitle()).build());
        filesService.makeFiles(new ArrayList<>(Arrays.asList(params.getAudioFile())), book);
        imageService.makeImage(params.getImageFile(), book);
        return book;
    }
}
