package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.Book;
import com.ssafy.sopy.domain.entity.Bookmark;
import com.ssafy.sopy.domain.entity.User;
import com.ssafy.sopy.domain.repository.BookRepository;
import com.ssafy.sopy.domain.repository.BookmarkRepository;
import com.ssafy.sopy.domain.repository.UserRepository;
import com.ssafy.sopy.dto.BookDto;
import com.ssafy.sopy.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.bookRepository = bookRepository;
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
    public Integer getBookmark(Long bookId){
        return bookmarkRepository.getByBookAndUser(bookRepository.getById(bookId), getUser()).getPage();
    };

    public List<BookDto> getReadBookList(){
        List<Book> books = bookmarkRepository.getByUser(getUser());
        List<BookDto> results = new ArrayList<>();
        for (Book book : books) {
            results.add(book.entityToDto());
        }
        return results;
    }
    private User getUser() {
        String s = SecurityUtil.getCurrentUsername().get();
        return userRepository.findByEmail(s);
    }
}
