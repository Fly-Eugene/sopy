package com.ssafy.sopy.domain.entity;

import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("user")
public class UserImage extends Image{

    public UserImage() {
        super();
    }

    @Builder
    public UserImage(Long id, String imageName, String iamgeOrgName, String path, String thumbnail) {
        super(id, imageName, iamgeOrgName, path, thumbnail);
    }
}