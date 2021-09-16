package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BookDto {
    private Long id;
    private String title;
    private String introduce;
    private String genre;

    public BookDto() {
    }

    @Builder
    public BookDto(Long id, String title, String introduce, String genre) {
        this.id = id;
        this.title = title;
        this.introduce = introduce;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", introduce='" + introduce + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
