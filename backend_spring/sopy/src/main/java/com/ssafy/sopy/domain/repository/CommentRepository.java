package com.ssafy.sopy.domain.repository;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment save(Comment comment);
    Comment getById(Long id);
    void delete(Comment comment);
    List<Comment> getByBook(Book book);
}
