package com.ssafy.sopy.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.QBookmark;
import com.ssafy.sopy.domain.entity.User;

import java.util.List;

import static com.ssafy.sopy.domain.entity.QBookmark.*;

public class BookmarkRepositoryImpl implements BookmarkRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public BookmarkRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Book> getByUser(User user){
        return jpaQueryFactory.select(bookmark.book).from(bookmark).where(bookmark.user.eq(user)).fetch();
    };
}
