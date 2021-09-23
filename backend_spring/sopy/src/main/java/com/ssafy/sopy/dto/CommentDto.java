package com.ssafy.sopy.dto;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentDto {
    private Long id;
    private String content;
    private Long bookId;
    private Long userId;

    public CommentDto() {
    }

    @Builder
    public CommentDto(Long id, String content, Long bookId, Long userId) {
        this.id = id;
        this.content = content;
        this.bookId = bookId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", bookId=" + bookId +
                ", userId=" + userId +
                '}';
    }
}
