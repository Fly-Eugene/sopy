package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.BookImage;
import com.ssafy.sopy.domain.entity.Image;
import com.ssafy.sopy.domain.entity.UserImage;
import com.ssafy.sopy.domain.repository.BookImageRepository;
import com.ssafy.sopy.domain.repository.BookRepository;
import com.ssafy.sopy.domain.repository.UserImageRepository;
import com.ssafy.sopy.util.FileUtil;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {
    private final BookRepository bookRepository;
    private final BookImageRepository bookImageRepository;
    private final UserImageRepository userImageRepository;
    private final FileUtil fileUtil;
    private final UploadService s3Service;

    public ImageService(BookRepository bookRepository, BookImageRepository bookImageRepository, UserImageRepository userImageRepository, FileUtil fileUtil, UploadService s3Service) {
        this.bookRepository = bookRepository;
        this.bookImageRepository = bookImageRepository;
        this.userImageRepository = userImageRepository;
        this.fileUtil = fileUtil;
        this.s3Service = s3Service;
    }

    @Transactional
    public BookImage makeBookImage(MultipartFile imageFile) throws IOException {
        if(imageFile.getSize() <= 0) return null;
        File file = fileUtil.setImage(imageFile);
        s3Service.uploadFile(file.getParent(), file.getName(), file);
        FileNode fileNode = getFileNode(file.getParent(), file.getName());
        return bookImageRepository.save(BookImage.builder()
                .imageName(fileNode.name)
                .imageOrgName(imageFile.getOriginalFilename())
                .path(fileNode.path)
                .thumbnail(fileUtil.setThumbnail(file)).build());
    }

    @Transactional
    public UserImage makeUserImage(MultipartFile imageFile) throws IOException {
        if(imageFile.getSize() <= 0) return null;
        File file = fileUtil.setImage(imageFile);
        s3Service.uploadFile(file.getParent(), file.getName(), file);
        FileNode fileNode = getFileNode(file.getParent(), file.getName());
        return userImageRepository.save(UserImage.builder()
                .imageName(fileNode.name)
                .imageOrgName(imageFile.getOriginalFilename())
                .path(fileNode.path)
                .thumbnail(fileUtil.setThumbnail(file)).build());
    }

    @Transactional
    public String getImage(Long bookId, Long memberId) {
        Book book = bookRepository.getById(bookId);
        Image image = null;
        if (bookId == null) {
            image = userImageRepository.getById(memberId);
        } else {
            image = bookImageRepository.getById(book.getBookImage().getId());
        }
        return image == null ? null : (image.getPath() + image.getImageName());
    }

    class FileNode{
        String path;
        String name;

        public FileNode(String path, String name) {
            this.path = path;
            this.name = name;
        }
    }
    public FileNode getFileNode(String path, String name){
        String fileUrl = s3Service.getFileUrl(path, name);
        int idx = fileUrl.lastIndexOf("/")+1;
        return new FileNode(fileUrl.substring(0, idx), fileUrl.substring(idx));
    }
}
