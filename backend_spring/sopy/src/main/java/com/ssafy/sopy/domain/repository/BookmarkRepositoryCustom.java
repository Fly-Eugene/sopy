package com.ssafy.sopy.domain.repository;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.User;

import java.util.List;

public interface BookmarkRepositoryCustom {
    List<Book> getByUser(User user);
}
