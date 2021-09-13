package com.ssafy.sopy.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    private String name;
    private String category;
    private Integer page;
    private String publisher;
    @Column(name = "publish_date")
    private LocalDate publishDate;

    public Book() {
    }

    @Builder
    public Book(Long id, String name, String category, Integer page, String publisher, LocalDate publishDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.page = page;
        this.publisher = publisher;
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", page=" + page +
                ", publisher='" + publisher + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
