package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLikeDto {
    private Long id;
    private UserDto user;
    private BookDto book;

    public UserLikeDto() {
    }

    @Builder
    public UserLikeDto(Long id, UserDto user, BookDto book) {
        this.id = id;
        this.user = user;
        this.book = book;
    }

}
