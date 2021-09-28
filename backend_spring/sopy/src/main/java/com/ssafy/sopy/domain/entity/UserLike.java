package com.ssafy.sopy.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public UserLike() {
    }

    @Builder
    public UserLike(Long id, User user, Book book) {
        this.id = id;
        this.user = user;
        this.book = book;
    }

    @Override
    public String toString() {
        return "UserLike{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
