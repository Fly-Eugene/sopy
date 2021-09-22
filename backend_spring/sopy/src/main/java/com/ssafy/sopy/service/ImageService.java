package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.BookImage;
import com.ssafy.sopy.domain.entity.UserImage;
import com.ssafy.sopy.domain.repository.BookImageRepository;
import com.ssafy.sopy.domain.repository.UserImageRepository;
import com.ssafy.sopy.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {
    private final BookImageRepository bookImageRepository;
    private final UserImageRepository userImageRepository;
    private final FileUtil fileUtil;

    public ImageService(BookImageRepository bookImageRepository, UserImageRepository userImageRepository, FileUtil fileUtil) {
        this.bookImageRepository = bookImageRepository;
        this.userImageRepository = userImageRepository;
        this.fileUtil = fileUtil;
    }

    public BookImage makeBookImage(MultipartFile imageFile) throws IOException {
        if(imageFile.getSize() <= 0) return null;
        File file = fileUtil.setImage(imageFile);
        return bookImageRepository.save(BookImage.builder().imageName(file.getName())
                .imageOrgName(imageFile.getOriginalFilename())
                .path(file.getParent() + "/")
                .thumbnail(fileUtil.setThumbnail(file)).build());
    }
    public UserImage makeUserImage(MultipartFile imageFile) throws IOException {
        if(imageFile.getSize() <= 0) return null;
        File file = fileUtil.setImage(imageFile);
        return userImageRepository.save(UserImage.builder().imageName(file.getName())
                .imageOrgName(imageFile.getOriginalFilename())
                .path(file.getPath() + "/")
                .thumbnail(fileUtil.setThumbnail(file)).build());
    }
}
