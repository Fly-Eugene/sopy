package com.ssafy.sopy.domain.repository;
import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.BookImage;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> getBooks();
    List<Book> searchBook(String title);
    List<Book> genreFilter(String genre);
    BookImage getBookImage(Long bookId);

}
