package com.ssafy.sopy.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import static com.ssafy.sopy.domain.entity.QUserLike.userLike;
import com.ssafy.sopy.dto.LikeReqDto;
import org.springframework.stereotype.Repository;

@Repository
public class UserLikeRepositoryImpl implements UserLikeRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public UserLikeRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Object cancel(LikeReqDto params) {
        return jpaQueryFactory.delete(userLike)
                .where(userLike.user.id.eq(params.getUserId()), userLike.book.id.eq(params.getBookId()))
                .execute();
    }
}
