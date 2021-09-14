package com.ssafy.sopy.domain.repository;

import com.ssafy.sopy.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book save(Book book);
}
