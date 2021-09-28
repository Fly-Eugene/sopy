import React from 'react';
import './BookDetailTitle.modules.scss'
import bookCover from '../../img/book-cover.jpg'
import { FaHeart, FaRegHeart } from 'react-icons/fa'

export default function BookDetailTitle(props) {
  const book = props.book;
  return (
    <div className="title-container">
      <div className="title-inner">
        <div className="title-left">
          <img src={bookCover} alt=""bookCover/>

        </div>
        <div className="title-right">
            <FaRegHeart />
          <div className="book-title">
            <p className="book-name">{book.title}</p>
            <p className="book-writer">{book.author}</p>
            <p className="book-translator">{book.translator} 옮김</p>
            <div className="book-info">
              <div>
                <p>장르</p>
                <p>{book.genre}</p>
              </div>
              <div>
                <p>재생시간</p>
                <p>00:43:19</p>
              </div>
              <div>
                <p>출판사</p>
                <p>{book.publisher}</p>
              </div>
              <div>
                <p>발간일</p>
                <p>{book.publishedDate}</p>
              </div>
            </div>
          </div>
          <select className="voice" name="voice">
            <option value=''>성우 선택</option>
            <option value='성우1'>성우1</option>
            <option value='성우2'>성우2</option>
            <option value='성우3'>성우3</option>
          </select>
        </div>
      </div>
    </div>
  )
}