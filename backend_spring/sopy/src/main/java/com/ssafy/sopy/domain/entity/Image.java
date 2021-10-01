package com.ssafy.sopy.domain.entity;

import com.ssafy.sopy.dto.ImageDto;
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

    @Column(name = "image_org_name")
    private String imageOrgName;

    private String path;
    private String thumbnail;

    public Image() {
    }

    public Image(Long id, String imageName, String imageOrgName, String path, String thumbnail) {
        this.id = id;
        this.imageName = imageName;
        this.imageOrgName = imageOrgName;
        this.path = path;
        this.thumbnail = thumbnail;
    }

    public ImageDto entityToDto(){
        return ImageDto.builder().id(id).imageName(imageName).imageOrgName(imageOrgName).path(path).thumbnail(thumbnail).build();
    }
}
