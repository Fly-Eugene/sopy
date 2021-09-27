package com.ssafy.sopy.domain.entity;

import com.ssafy.sopy.dto.FilesDto;
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

//  파일 사용하는 게시판 형태가 더 생기면 각 게시판의 file이 이 files를 상속 받도록 리팩토링 해줘야할듯
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

    public FilesDto entityToDto(){
        return FilesDto.builder().id(id).orgName(orgName).sysName(sysName).path(path).fileSize(fileSize)
                .fileType(fileType).regTime(regTime).bookId(book.getId()).build();
    }
}
