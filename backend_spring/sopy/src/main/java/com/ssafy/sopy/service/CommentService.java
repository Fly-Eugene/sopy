package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.Comment;
import com.ssafy.sopy.domain.repository.BookRepository;
import com.ssafy.sopy.domain.repository.CommentRepository;
import com.ssafy.sopy.domain.repository.UserRepository;
import com.ssafy.sopy.dto.CommentDto;
import com.ssafy.sopy.util.SecurityUtil;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Object create(CommentDto params) {
        Comment result = commentRepository.save(Comment.builder()
                .content(params.getContent())
                .user(userRepository.getById(getUserId()))
                .book(bookRepository.getById(params.getBookId()))
                .build());
        return result.entityToDto();
    }

    @Transactional
    public Object update(CommentDto params) throws AuthenticationException {
        Comment comment = authCheck(params.getId());
        Comment result = commentRepository.save(Comment.builder()
                .id(params.getId())
                .content(params.getContent() == null ? comment.getContent() : params.getContent())
                .book(comment.getBook())
                .user(comment.getUser())
                .build());
        return result.entityToDto();
    }

    @Transactional
    public void delete(Long commentId) throws AuthenticationException {
        commentRepository.delete(authCheck(commentId));
    }


    public Object getComments(Long bookId){
        List<Comment> comments = commentRepository.getByBook(bookRepository.getById(bookId));
        List<CommentDto> results = new ArrayList<>();
        for (Comment comment : comments) {
            results.add(comment.entityToDto());
        }
        return results;
    }

    private Comment authCheck(Long commentId) throws AuthenticationException {
        Comment comment = commentRepository.getById(commentId);
        if (comment.getUser().getId() != getUserId()) throw new AuthenticationException("해당 댓글 작성자만 삭제 가능합니다.");
        return comment;
    }

    private Long getUserId() {
        String s = SecurityUtil.getCurrentUsername().get();
        return userRepository.findByEmail(s).getId();
    }
}
