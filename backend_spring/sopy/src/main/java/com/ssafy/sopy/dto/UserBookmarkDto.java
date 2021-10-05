package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserBookmarkDto {
    private Long id;
    private Integer page;
    private Long bookId;
    private Long userId;

    public UserBookmarkDto() {
    }

    @Builder
    public UserBookmarkDto(Long id, Integer page, Long bookId, Long userId) {
        this.id = id;
        this.page = page;
        this.bookId = bookId;
        this.userId = userId;
    }
}
