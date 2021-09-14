package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.repository.FilesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesService {
    private final FilesRepository filesRepository;

    public FilesService(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    public Object makeFile(MultipartFile file){}
}
