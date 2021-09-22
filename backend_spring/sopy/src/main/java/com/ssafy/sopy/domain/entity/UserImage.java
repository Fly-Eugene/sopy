package com.ssafy.sopy.domain.entity;

import com.ssafy.sopy.dto.ImageDto;
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
    public UserImage(Long id, String imageName, String imageOrgName, String path, String thumbnail) {
        super(id, imageName, imageOrgName, path, thumbnail);
    }
}
