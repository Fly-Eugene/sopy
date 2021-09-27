package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FilesDto {
    private Long id;
    private String orgName;
    private String sysName;
    private String path;
    private Long fileSize;
    private String fileType;
    private LocalDateTime regTime;
    private Long bookId;

    public FilesDto() {
    }

    @Builder
    public FilesDto(Long id, String orgName, String sysName, String path, Long fileSize, String fileType, LocalDateTime regTime, Long bookId) {
        this.id = id;
        this.orgName = orgName;
        this.sysName = sysName;
        this.path = path;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.regTime = regTime;
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "FilesDto{" +
                "id=" + id +
                ", orgName='" + orgName + '\'' +
                ", sysName='" + sysName + '\'' +
                ", path='" + path + '\'' +
                ", fileSize=" + fileSize +
                ", fileType='" + fileType + '\'' +
                ", regTime=" + regTime +
                ", bookId=" + bookId +
                '}';
    }
}
