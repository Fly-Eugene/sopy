package com.ssafy.sopy.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sopy.domain.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.sopy.domain.entity.QBook.book;

@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public BookRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Book> getBooks() {
        return jpaQueryFactory.selectFrom(book).from(book).fetch();
    }
}
