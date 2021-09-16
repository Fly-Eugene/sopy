package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.Files;
import com.ssafy.sopy.domain.repository.BookRepository;
import com.ssafy.sopy.dto.BookAudioReqDto;
import com.ssafy.sopy.dto.BookReqDto;
import com.ssafy.sopy.util.HttpURLConnectionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final FilesService filesService;
    private final ImageService imageService;
    private final HttpURLConnectionUtil httpURLConnectionUtil;
    private final String djangoURL;

    public BookService(BookRepository bookRepository, FilesService filesService, ImageService imageService, HttpURLConnectionUtil httpURLConnectionUtil,
                       @Value("${djangoURL}") String djangoURL) {
        this.bookRepository = bookRepository;
        this.filesService = filesService;
        this.imageService = imageService;
        this.httpURLConnectionUtil = httpURLConnectionUtil;
        this.djangoURL = djangoURL;
    }

    @Transactional
    public Object makeBook(BookReqDto params) throws IOException {
        Book book = bookRepository.save(Book.builder().id(params.getId()).genre(params.getGenre()).introduce(params.getIntroduce()).title(params.getTitle()).build());
        filesService.makeFiles(new ArrayList<>(Arrays.asList(params.getAudioFile())), book);
        imageService.makeImage(params.getImageFile(), book);
        return book;
    }

    @Transactional
    public Object makeAudio(BookAudioReqDto params, Long bookId) throws IOException {
        Book book = bookRepository.getById(bookId);
        Map<String, String> jsonData = new HashMap<String, String>();
        String textPath = null;
        if(params.getImageFile().getSize() > 0){
            Files imageFile = filesService.makeFiles(new ArrayList<>(Arrays.asList(params.getImageFile())), book).get(0);
            jsonData.put("path", imageFile.getPath());
            jsonData.put("name", imageFile.getSysName());
            httpURLConnectionUtil.post(djangoURL + "/book/ocr", jsonData);
        }
        if(params.getTextFile().getSize() > 0){
            Files textFile = filesService.makeFiles(new ArrayList<>(Arrays.asList(params.getTextFile())), book).get(0);
            textPath = textFile.getPath() + textFile.getSysName();
        } else{
//            textPath =
        }
        // TTS 요청 후 audio파일 DB에 저장
//        PathNode pathNode = pathSplit(textPath);
//        jsonData.put("path", pathNode.path);
//        jsonData.put("name", pathNode.name);
//        httpURLConnectionUtil.post(djangoURL + "/book/tts", jsonData);
        return null;
    }
    public PathNode pathSplit(String fullPath) {
        int index = fullPath.lastIndexOf("/");
        if (index != -1) {
            return new PathNode(fullPath.substring(0, index+1), fullPath.substring(index+1));
        }
        return new PathNode("", "");
    }
    class PathNode {
        String path, name;
        public PathNode() {
        }
        public PathNode(String path, String name) {
            this.path = path;
            this.name = name;
        }
    }
}
