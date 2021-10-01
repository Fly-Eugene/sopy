package com.ssafy.sopy.dto;

import com.ssafy.sopy.domain.entity.Image;
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
    private ImageDto bookImage;
    private String dirPath;
    private Integer pageSize;

    public BookDto() {
    }


    @Builder
    public BookDto(Long id, String title, String introduce, String genre, String author, String translator, String publisher, String publishedDate, ImageDto bookImage, String dirPath, Integer pageSize) {
        this.id = id;
        this.title = title;
        this.introduce = introduce;
        this.genre = genre;
        this.author = author;
        this.translator = translator;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.bookImage = bookImage;
        this.dirPath = dirPath;
        this.pageSize = pageSize;
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
                ", pageSize='" + pageSize + '\'' +
                '}';
    }
}
