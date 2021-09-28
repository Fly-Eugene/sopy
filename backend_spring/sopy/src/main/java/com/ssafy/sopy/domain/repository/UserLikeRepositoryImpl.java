package com.ssafy.sopy.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import static com.ssafy.sopy.domain.entity.QUserLike.userLike;

import com.ssafy.sopy.domain.entity.UserLike;
import com.ssafy.sopy.dto.LikeReqDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserLikeRepositoryImpl implements UserLikeRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public UserLikeRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Object cancel(Long userId, Long bookId) {
        return jpaQueryFactory.delete(userLike)
                .where(userLike.user.id.eq(userId), userLike.book.id.eq(bookId))
                .execute();
    }

    @Override
    public List<UserLike> getLikeBooks(Long userId) {
        return jpaQueryFactory.select(userLike).from(userLike).where(userLike.user.id.eq(userId)).fetch();
    }
}
