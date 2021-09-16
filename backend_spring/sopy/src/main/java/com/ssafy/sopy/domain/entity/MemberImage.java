package com.ssafy.sopy.domain.entity;


import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("member")
public class MemberImage extends Image {

    public MemberImage() {
        super();
    }

    @Builder
    public MemberImage(Long id, String imageName, String iamgeOrgName, String path, String thumbnail) {
        super(id, imageName, iamgeOrgName, path, thumbnail);
    }
}
