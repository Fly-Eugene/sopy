package com.ssafy.sopy.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sopy.domain.entity.QUserLike;
import com.ssafy.sopy.domain.entity.UserLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.sopy.domain.entity.QUser.user;
import static com.ssafy.sopy.domain.entity.QUserLike.*;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

}
