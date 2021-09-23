package com.ssafy.sopy.controller;

import com.ssafy.sopy.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Path;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{bookId}")
    public ResponseEntity<Resource> sendBookImg(@PathVariable("bookId") Long bookId) {
        String bookImage = imageService.getImage(bookId, null);
        Path path = new File(bookImage).toPath();
        return getResponseEntity(path);
    }

    private ResponseEntity<Resource> getResponseEntity(Path path) {
        FileSystemResource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.IMAGE_JPEG_VALUE))
                .body(resource);

    }

}
