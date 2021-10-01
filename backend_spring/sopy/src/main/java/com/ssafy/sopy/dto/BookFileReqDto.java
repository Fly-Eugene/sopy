package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookFileReqDto {
    private MultipartFile pdfFile;
    private List<MultipartFile> textFiles = new ArrayList<>();
    private List<MultipartFile> imageFiles = new ArrayList<>();

    public BookFileReqDto() {
    }

    @Builder
    public BookFileReqDto(MultipartFile pdfFile, List<MultipartFile> textFiles, List<MultipartFile> imageFiles) {
        this.pdfFile = pdfFile;
        this.textFiles = textFiles;
        this.imageFiles = imageFiles;
    }
}
