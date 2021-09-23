package com.ssafy.sopy.domain.entity;

import com.ssafy.sopy.dto.CommentDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment() {
    }

    @Builder
    public Comment(Long id, String content, Book book, User user) {
        this.id = id;
        this.content = content;
        this.book = book;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
    public CommentDto entityToDto(){
        return CommentDto.builder().id(id).content(content).bookId(book.getId()).userId(user.getId()).build();
    }
}
