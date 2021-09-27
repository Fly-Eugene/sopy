package com.ssafy.sopy.domain.repository;

import com.ssafy.sopy.dto.LikeReqDto;

public interface UserLikeRepositoryCustom {
    Object cancel(LikeReqDto params);
}
