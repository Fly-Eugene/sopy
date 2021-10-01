package com.ssafy.sopy.domain.repository;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.Bookmark;
import com.ssafy.sopy.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, BookmarkRepositoryCustom {
    Bookmark save(Bookmark bookmark);
    Bookmark getByBookAndUser(Book book, User user);
}
