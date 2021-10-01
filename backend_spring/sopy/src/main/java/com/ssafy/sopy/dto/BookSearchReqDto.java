package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class BookSearchReqDto {
    private String title;

    public BookSearchReqDto() {
    }

    @Builder
    public BookSearchReqDto(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BookSearchReqDto{" +
                "title=" + title +
                "}";
    }
}
