package com.ssafy.sopy.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_ord_name")
    private String imageOrgName;

    private String path;
    private String thumbnail;

    public Image() {
    }

    // 생성자
    public Image(Long id, String imageName, String imageOrgName, String path, String thumbnail) {
        this.id = id;
        this.imageName = imageName;
        this.imageOrgName = imageOrgName;
        this.path = path;
        this.thumbnail = thumbnail;
    }
}
