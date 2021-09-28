package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeReqDto {
    private Long userId;
    private Long bookId;

    public LikeReqDto() {
    }

    @Builder
    public LikeReqDto(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

}
