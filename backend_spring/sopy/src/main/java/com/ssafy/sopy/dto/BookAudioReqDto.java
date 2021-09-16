package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BookAudioReqDto {
    private MultipartFile imageFile;
    private MultipartFile textFile;

    public BookAudioReqDto() {
    }

    @Builder
    public BookAudioReqDto(MultipartFile imageFile, MultipartFile textFile) {
        this.imageFile = imageFile;
        this.textFile = textFile;
    }
}
