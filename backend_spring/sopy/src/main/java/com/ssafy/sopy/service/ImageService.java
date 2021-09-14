package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.repository.BookImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final BookImageRepository bookImageRepository;

    public ImageService(BookImageRepository bookImageRepository) {
        this.bookImageRepository = bookImageRepository;
    }

}
