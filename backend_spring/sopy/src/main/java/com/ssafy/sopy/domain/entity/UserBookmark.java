package com.ssafy.sopy.domain.entity;

import com.ssafy.sopy.dto.UserBookmarkDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserBookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_bookmark_id")
    private Long id;
    private Integer page;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public UserBookmark() {
    }

    @Builder
    public UserBookmark(Long id, Integer page, Book book, User user) {
        this.id = id;
        this.page = page;
        this.book = book;
        this.user = user;
    }
    public UserBookmarkDto entityToDto(){
        return UserBookmarkDto.builder().id(id).page(page).bookId(book.getId()).userId(user.getId()).build();
    }
}
