package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.Bookmark;
import com.ssafy.sopy.domain.entity.User;
import com.ssafy.sopy.domain.repository.BookmarkRepository;
import com.ssafy.sopy.domain.repository.UserRepository;
import com.ssafy.sopy.util.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository, UserRepository userRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.userRepository = userRepository;
    }

    public void setBookmark(Book book, Integer bookPage){
        User user = getUser();
        Bookmark bookmark = bookmarkRepository.getByBookAndUser(book, user);
        if(bookmark == null){
            bookmarkRepository.save(Bookmark.builder().book(book).user(user).page(bookPage).build());
        } else{
            bookmarkRepository.save(Bookmark.builder().id(bookmark.getId()).book(book).user(user).page(bookPage).build());
        }
    }
    public Object getBookmark(){
        return null;
    };

    private User getUser() {
        String s = SecurityUtil.getCurrentUsername().get();
        return userRepository.findByEmail(s);
    }
}
