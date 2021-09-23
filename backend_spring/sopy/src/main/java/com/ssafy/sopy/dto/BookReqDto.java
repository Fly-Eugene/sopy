package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter // fileDto로 받아 올 때 빌더패턴만 적용하고 setter 없으니까 못 받아 오길래 넣어줌
public class BookReqDto {
    private MultipartFile imageFile;
//    private MultipartFile audioFile;
    private Long id;
    private String title;
    private String introduce;
    private String genre;

    private String author; // 지은이
    private String translator; // 옮김이
    private String publisher; // 출판사
    private String publishedDate; //발행일

    public BookReqDto() {
    }


    @Builder
    public BookReqDto(MultipartFile imageFile, Long id, String title, String introduce, String genre, String author, String translator, String publisher, String publishedDate) {
        this.imageFile = imageFile;
//        this.audioFile = audioFile;
        this.id = id;
        this.title = title;
        this.introduce = introduce;
        this.genre = genre;
        this.author = author;
        this.translator = translator;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
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
