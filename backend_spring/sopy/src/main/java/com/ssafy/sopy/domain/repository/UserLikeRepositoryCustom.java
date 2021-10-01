package com.ssafy.sopy.domain.repository;

import com.ssafy.sopy.domain.entity.UserLike;
import com.ssafy.sopy.dto.LikeReqDto;

import java.util.List;

public interface UserLikeRepositoryCustom {

    Object cancel(Long userId, Long bookId);
    List<UserLike> getLikeBooks(Long userId);
}
