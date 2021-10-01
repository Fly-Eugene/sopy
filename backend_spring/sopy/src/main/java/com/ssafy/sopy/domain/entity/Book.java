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
    private String dirPath;
    private Integer pageSize;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private BookImage bookImage;

    public Book() {
    }

    @Builder
    public Book(Long id, String title, String introduce, String genre, String author, String translator, String publisher, String publishedDate, List<Bookmark> bookmarkList, BookImage bookImage, String dirPath, Integer pageSize) {
        this.id = id;
        this.title = title;
        this.introduce = introduce;
        this.genre = genre;
        this.author = author;
        this.translator = translator;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.bookmarkList = bookmarkList;
        this.bookImage = bookImage;
        this.dirPath = dirPath;
        this.pageSize = pageSize;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", introduce='" + introduce + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", translator='" + translator + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", dirPath='" + dirPath + '\'' +
                ", bookmarkList=" + bookmarkList +
                ", bookImage=" + bookImage +
                ", pageSize=" + pageSize +
                '}';
    }

    public BookDto entityToDto() {
        return BookDto.builder()
                .id(id)
                .title(title)
                .introduce(introduce)
                .genre(genre)
                .author(author)
                .translator(translator)
                .publisher(publisher)
                .publishedDate(publishedDate)
                .bookImage(bookImage == null ? null : bookImage.entityToDto())
                .dirPath(dirPath)
                .pageSize(pageSize)
                .build();
    }
}