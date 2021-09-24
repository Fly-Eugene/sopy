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
    private MultipartFile textFile;
    private List<MultipartFile> imageFiles = new ArrayList<>();

    public BookFileReqDto() {
    }

    @Builder
    public BookFileReqDto(MultipartFile pdfFile, MultipartFile textFile, List<MultipartFile> imageFiles) {
        this.pdfFile = pdfFile;
        this.textFile = textFile;
        this.imageFiles = imageFiles;
    }
}
