package com.ssafy.sopy.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Bookmark {

    @Id @GeneratedValue
    private Long Id;

    // Book, User 엔티티 연결 추가 필요

    private Long page;
    private Long line;

    @Builder
    public Bookmark(Long id, Long page, Long line) {
        Id = id;
        this.page = page;
        this.line = line;
    }


}
