package com.ssafy.sopy.util;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.Files;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtil {

    public File makeDir(String preLoc, String postLoc) throws IOException {
        String uploadRoot = "c:/sopy/upload";
        String path = preLoc + new SimpleDateFormat("/yyyy/MM/dd").format(new Date()) + postLoc;
        File file = new File(uploadRoot + path);
        if(!file.exists()) file.mkdirs();
        return file;
    }
    public String getType(String name) {
        int index = name.lastIndexOf(".");
        if (index != -1) {
            return name.substring(index);
        }
        return "";
    }
    public File makeName(String name, File file) {
        String ext = getType(name);
        File f = new File(file.getPath(), UUID.randomUUID() + ext);
        return f;
    }
    public String setThumbnail(File f) throws IOException {
        // 썸네일 저장
        Thumbnails.of(f)
                .size(300, 200)
                .toFile(new File(f.getParent(), "thumb_" + f.getName()));
        return "thumb_" + f.getName();
    }

    public File setImage(MultipartFile mf) throws IOException {
        File f = makeName(mf.getOriginalFilename(), makeDir("", ""));
        mf.transferTo(f);
        return f;
    }

    public List<Files> setFiles(List<MultipartFile> files, Book book) throws IOException {
        File file = makeDir("/file", "");
        List<Files> fileEntities = new ArrayList<>();
        for(MultipartFile mf: files) {
            if(mf.getSize() == 0) continue;
            // file 생성
            String orgName = mf.getOriginalFilename();
            File f = makeName(orgName, file);
            mf.transferTo(f);
            // db 저장
            String contentType = getType(orgName);
            fileEntities.add(Files.builder().sysName(f.getName()).orgName(orgName).path(f.getParent() + "/")
                    .fileSize(mf.getSize()).fileType(contentType).regTime(LocalDateTime.now()).book(book).build());
        }
        return fileEntities;
    }
}
