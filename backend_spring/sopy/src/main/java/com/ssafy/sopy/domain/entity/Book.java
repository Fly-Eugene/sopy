package com.ssafy.sopy.domain.entity;

import com.ssafy.sopy.dto.BookDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    private String title;
    private String introduce;
    private String genre;

    public Book() {
    }

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @Builder
    public Book(Long id, String title, String introduce, String genre) {
        this.id = id;
        this.title = title;
        this.introduce = introduce;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", introduce='" + introduce + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
    public BookDto entityToDto() {
        return BookDto.builder().id(id).title(title).introduce(introduce).genre(genre).build();
    }
}