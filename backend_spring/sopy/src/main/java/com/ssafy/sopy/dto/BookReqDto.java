package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter // fileDto로 받아 올 때 빌더패턴만 적용하고 setter 없으니까 못 받아 오길래 넣어줌
public class BookReqDto {
    private MultipartFile imageFile;
    private MultipartFile audioFile;
    private Long id;
    private String title;
    private String introduce;
    private String genre;

    public BookReqDto() {
    }

    @Builder
    public BookReqDto(MultipartFile imageFile, MultipartFile audioFile, Long id, String title, String introduce, String genre) {
        this.imageFile = imageFile;
        this.audioFile = audioFile;
        this.id = id;
        this.title = title;
        this.introduce = introduce;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "BookReqDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", introduce='" + introduce + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
