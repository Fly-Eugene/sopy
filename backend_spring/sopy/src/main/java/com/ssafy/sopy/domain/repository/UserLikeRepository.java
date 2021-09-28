package com.ssafy.sopy.domain.repository;

import com.ssafy.sopy.domain.entity.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikeRepository extends JpaRepository<UserLike, Long>, UserLikeRepositoryCustom {
    UserLike save(UserLike userLike);
}
