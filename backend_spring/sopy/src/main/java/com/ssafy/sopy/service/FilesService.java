package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.Files;
import com.ssafy.sopy.domain.repository.FilesRepository;
import com.ssafy.sopy.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FilesService {
    private final FilesRepository filesRepository;
    private final FileUtil fileUtil;

    public FilesService(FilesRepository filesRepository, FileUtil fileUtil) {
        this.filesRepository = filesRepository;
        this.fileUtil = fileUtil;
    }

    public File saveDir(File dir, Book book){
        for (File file : dir.listFiles()) {
            if (file.isFile())
                filesRepository.save(Files.builder().sysName(file.getName()).fileSize(file.length())
                        .fileType(fileUtil.getType(file.getName())).path(file.getParent()).regTime(LocalDateTime.now()).book(book).build());
            else
                saveDir(file, book);
        }
        return null;
    }
    public List<Files> makeFiles(List<MultipartFile> mfList, Book book) throws IOException {
        List<Files> files = fileUtil.setFiles(mfList, book);
        for (Files file : files) {
            filesRepository.save(file);
        }
        return files;
    }
}
