package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.BookImage;
import com.ssafy.sopy.domain.repository.BookImageRepository;
import com.ssafy.sopy.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {
    private final BookImageRepository bookImageRepository;
    private final FileUtil fileUtil;

    public ImageService(BookImageRepository bookImageRepository, FileUtil fileUtil) {
        this.bookImageRepository = bookImageRepository;
        this.fileUtil = fileUtil;
    }

    public void makeImage(MultipartFile imageFile, Book book) throws IOException {
        File file = fileUtil.setImage(imageFile);
        bookImageRepository.save(BookImage.builder().imageName(file.getName())
                .imageOrgName(imageFile.getOriginalFilename())
                .path(file.getParent() + "/")
                .thumbnail(fileUtil.setThumbnail(file)).build());
    }
}
