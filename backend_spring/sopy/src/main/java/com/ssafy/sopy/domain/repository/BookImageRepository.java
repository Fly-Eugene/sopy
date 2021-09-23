package com.ssafy.sopy.domain.repository;

import com.ssafy.sopy.domain.entity.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookImageRepository extends JpaRepository<BookImage, Long> {
    BookImage save(BookImage bookImage);
    BookImage getById(Long BookId);
}
