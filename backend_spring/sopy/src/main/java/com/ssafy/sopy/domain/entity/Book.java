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
    private String author; // 지은이
    private String translator; // 옮김이
    private String publisher; // 출판사
    private String publishedDate; //발행일

    public Book() {
    }

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private BookImage bookImage;


    @Builder
    public Book(Long id, String title, String introduce, String genre, String author, String translator, String publisher, String publishedDate, List<Bookmark> bookmarkList) {
        this.id = id;
        this.title = title;
        this.introduce = introduce;
        this.genre = genre;
        this.author = author;
        this.translator = translator;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
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
        return BookDto.builder().id(id).title(title).introduce(introduce).genre(genre).author(author).translator(translator).publisher(publisher).publishedDate(publishedDate).build();
    }
}