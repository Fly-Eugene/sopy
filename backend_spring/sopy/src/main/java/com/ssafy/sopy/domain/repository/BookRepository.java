package com.ssafy.sopy.domain.repository;

import com.ssafy.sopy.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
    Book save(Book book);
    Book getById(Long id);
}
