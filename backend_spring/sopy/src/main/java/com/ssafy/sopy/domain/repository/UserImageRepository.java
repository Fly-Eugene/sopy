package com.ssafy.sopy.domain.repository;

import com.ssafy.sopy.domain.entity.BookImage;
import com.ssafy.sopy.domain.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
    UserImage save(UserImage userImage);
    UserImage getById(Long userId);
}
