package com.ssafy.sopy.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Bookmark {

    @Id @GeneratedValue
    private Long Id;
    private Integer page;

    // xToOne의 기본 fetch 타입은 Eager 이므로 Lazy로 변경한다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Bookmark(Long id, Integer page, Book book, User user) {
        Id = id;
        this.page = page;
        this.book = book;
        this.user = user;
    }
}
