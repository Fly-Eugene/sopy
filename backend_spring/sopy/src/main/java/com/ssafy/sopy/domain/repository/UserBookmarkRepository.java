package com.ssafy.sopy.domain.repository;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.User;
import com.ssafy.sopy.domain.entity.UserBookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookmarkRepository extends JpaRepository<UserBookmark, Long> {
    UserBookmark save(UserBookmark userBookmark);
    UserBookmark getByBookAndUser(Book book, User user);
}
