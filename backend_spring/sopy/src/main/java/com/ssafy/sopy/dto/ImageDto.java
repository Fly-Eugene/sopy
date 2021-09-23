package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageDto {
    private Long id;
    private String imageName;
    private String imageOrgName;
    private String path;
    private String thumbnail;

    public ImageDto() {
    }

    @Builder
    public ImageDto(Long id, String imageName, String imageOrgName, String path, String thumbnail) {
        this.id = id;
        this.imageName = imageName;
        this.imageOrgName = imageOrgName;
        this.path = path;
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "id=" + id +
                ", imageName='" + imageName + '\'' +
                ", imageOrgName='" + imageOrgName + '\'' +
                ", path='" + path + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
