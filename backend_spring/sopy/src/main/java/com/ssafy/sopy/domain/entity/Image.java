package com.ssafy.sopy.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorColumn(name = "DTYPE")
public class Image {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_ord_name")
    private String iamgeOrgName;

    private String path;
    private String thumbnail;

    @Builder
    public Image(Long id, String imageName, String iamgeOrgName, String path, String thumbnail) {
        Id = id;
        this.imageName = imageName;
        this.iamgeOrgName = iamgeOrgName;
        this.path = path;
        this.thumbnail = thumbnail;
    }


}
