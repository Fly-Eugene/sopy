import React from 'react';
import Book from '../../common/component/Book'
import underImgSrc from '../../img/book_under.png';
import bookCover from '../../img/book-cover.jpg'
import nextBtn from '../../img/nextBtn.png'
import prevBtn from '../../img/prevBtn.png'
import './UserReadBook.modules.scss'

export default function UserReadBook() {
  const prev = () => {
    return 
  }
  const next = () => {
    return
  }
  return (
    <div className="read-book-container">
      <div className="read-book-inner">
        <div className="book-header">
          <span>내가 읽은 책</span>
          <span>1 / 20</span>
        </div>
        <div className="book-container">
          <Book underImgSrc={underImgSrc} bookCover={bookCover}/>
          <Book underImgSrc={underImgSrc} bookCover={bookCover}/>
          <Book underImgSrc={underImgSrc} bookCover={bookCover}/>
        </div>
      </div>
      <div className="read-botton-direction">
        <div className="prevBtn" onClick={prev}>
          <img src={prevBtn} alt="prevBtn"/>
        </div>
        <div className="nextBtn" onClick={next}>
          <img src={nextBtn} alt="nextBtn"/>
        </div>
      </div>
    </div>
  );
}