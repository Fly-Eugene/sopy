package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.Bookmark;
import com.ssafy.sopy.domain.entity.User;
import com.ssafy.sopy.domain.entity.UserBookmark;
import com.ssafy.sopy.domain.repository.BookRepository;
import com.ssafy.sopy.domain.repository.UserBookmarkRepository;
import com.ssafy.sopy.domain.repository.UserRepository;
import com.ssafy.sopy.dto.UserBookmarkDto;
import com.ssafy.sopy.util.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class UserBookmarkService {
    private final UserBookmarkRepository userBookmarkRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public UserBookmarkService(UserBookmarkRepository userBookmarkRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.userBookmarkRepository = userBookmarkRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public UserBookmarkDto setUserBookmark(UserBookmarkDto params) {
        Book book = bookRepository.getById(params.getBookId());
        User user = getUser();
        UserBookmark userBookmark = userBookmarkRepository.getByBookAndUser(book, user);
        System.out.println("user + book = " + user + book);
        if (userBookmark == null){
            return userBookmarkRepository.save(UserBookmark.builder().book(book).user(user).page(params.getPage()).build()).entityToDto();
        } else {
            return userBookmarkRepository.save(UserBookmark.builder().id(userBookmark.getId()).book(book).user(user).page(params.getPage()).build()).entityToDto();
        }
    }

    public Object getUserBookmark(Long bookId) {
        UserBookmark userBookmark = userBookmarkRepository.getByBookAndUser(bookRepository.getById(bookId), getUser());
        return userBookmark == null ? 0 : userBookmark.getPage();
    }

    public void deleteUserBookmark(Long bookId) {
        Book book = bookRepository.getById(bookId);
        User user = getUser();
        userBookmarkRepository.delete(userBookmarkRepository.getByBookAndUser(book, user));
    }

    private User getUser() {
        String s = SecurityUtil.getCurrentUsername().get();
        return userRepository.findByEmail(s);
    }

}
