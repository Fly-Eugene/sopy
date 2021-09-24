import React from 'react';
import './UserLikeBook.modules.scss'
import Book from '../../common/component/Book'
import underImgSrc from '../../img/book_under.png';
import bookCover from '../../img/book-cover.jpg'
import nextBtn from '../../img/nextBtn.png'
import prevBtn from '../../img/prevBtn.png'

export default function UserLikeBook() {
  const prev = () => {
    return 
  }
  const next = () => {
    return
  }
  return (
    <div className="like-book-container">
      <div className="like-book-inner">
        <div className="book-header">
          <span>좋아하는 책</span>
          <span>1 / 20</span>
        </div>
        <div className="book-container">
          <Book underImgSrc={underImgSrc} bookCover={bookCover}/>
          <Book underImgSrc={underImgSrc} bookCover={bookCover}/>
          <Book underImgSrc={underImgSrc} bookCover={bookCover}/>
        </div>
      </div>
      <div className="like-botton-direction">
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