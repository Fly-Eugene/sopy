package com.ssafy.sopy.controller;

import com.ssafy.sopy.dto.CommentDto;
import com.ssafy.sopy.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Object create(@RequestBody CommentDto params){
        return commentService.create(params);
    }

    @PatchMapping
    public Object update(@RequestBody CommentDto params) throws AuthenticationException {
        return commentService.update(params);
    }

    @DeleteMapping("/{commentId}")
    public Object delete(@PathVariable(name = "commentId") Long commentId) throws AuthenticationException {
        commentService.delete(commentId);
        return null;
    }

    @GetMapping
    public Object getComments(@RequestParam Long bookId) {
        return commentService.getComments(bookId);
    }

}
