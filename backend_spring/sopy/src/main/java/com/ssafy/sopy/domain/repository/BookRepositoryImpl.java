package com.ssafy.sopy.domain.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.BookImage;
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

    @Override
    public List<Book> searchBook(String title) {
        return jpaQueryFactory.selectDistinct(book).from(book).where(titleIn(title)).fetch();
    }

    @Override
    public List<Book> genreFilter(String genre) {
        return jpaQueryFactory.selectDistinct(book).from(book).where(book.genre.eq(genre)).fetch();
    }

    @Override
    public BookImage getBookImage(Long bookId) {
        return jpaQueryFactory.select(book.bookImage).from(book).where(book.id.eq(bookId)).fetchOne();
    }

    private BooleanExpression titleIn(String title) {
        return title == null ? null : book.title.eq(title);
    }
}
