package com.ssafy.sopy.domain.entity;

import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("book")
public class BookImage extends Image {

    public BookImage() {
        super();
    }

    @Builder
    public BookImage(Long id, String imageName, String imageOrgName, String path, String thumbnail) {
        super(id, imageName, imageOrgName, path, thumbnail);
    }
}
