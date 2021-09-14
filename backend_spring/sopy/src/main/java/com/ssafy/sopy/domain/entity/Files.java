package com.ssafy.sopy.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "files_id")
    private Long id;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "sys_name")
    private String sysName;

    @Column(name = "path")
    private String path;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "reg_time")
    private LocalDateTime regTime;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Files() {
    }

    @Builder
    public Files(Long id, String orgName, String sysName, String path, Long fileSize, String fileType, LocalDateTime regTime, Book book) {
        this.id = id;
        this.orgName = orgName;
        this.sysName = sysName;
        this.path = path;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.regTime = regTime;
        this.book = book;
    }

    @Override
    public String toString() {
        return "Files{" +
                "id=" + id +
                ", orgName='" + orgName + '\'' +
                ", sysName='" + sysName + '\'' +
                ", path='" + path + '\'' +
                ", fileSize=" + fileSize +
                ", fileType='" + fileType + '\'' +
                ", regTime=" + regTime +
                ", book=" + book +
                '}';
    }
}
