package com.ssafy.sopy.domain.repository;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.Bookmark;
import com.ssafy.sopy.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Bookmark save(Bookmark bookmark);
    Bookmark getByBookAndUser(Book book, User user);
    List<Book> getByUser(User user);
}
