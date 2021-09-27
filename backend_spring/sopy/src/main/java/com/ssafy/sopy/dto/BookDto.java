package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BookDto {
    private Long id;
    private String title;
    private String introduce;
    private String genre;

    private String author; // 지은이
    private String translator; // 옮김이
    private String publisher; // 출판사
    private String publishedDate; //발행일
    private String dirPath;
    public BookDto() {
    }

    @Builder
    public BookDto(Long id, String title, String introduce, String genre, String author, String translator, String publisher, String publishedDate, String dirPath) {
        this.id = id;
        this.title = title;
        this.introduce = introduce;
        this.genre = genre;
        this.author = author;
        this.translator = translator;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.dirPath = dirPath;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", introduce='" + introduce + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", translator='" + translator + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", dirPath='" + dirPath + '\'' +
                '}';
    }
}
