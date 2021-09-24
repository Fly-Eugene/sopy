import React from 'react';
import './BookDetailTitle.modules.scss'
import bookCover from '../../img/book-cover.jpg'
import { FaHeart, FaRegHeart } from 'react-icons/fa'

export default function BookDetailTitle() {
  return (
    <div className="title-container">
      <div className="title-inner">
        <div className="title-left">
          <img src={bookCover} alt=""bookCover/>

        </div>
        <div className="title-right">
            <FaRegHeart />
          <div className="book-title">
            <p className="book-name">프란츠 카프카 단편선</p>
            <p className="book-writer">프란츠 카프카</p>
            <p className="book-translator">박병선 옮김</p>
            <div className="book-info">
              <div>
                <p>장르</p>
                <p>소설</p>
              </div>
              <div>
                <p>재생시간</p>
                <p>00:43:19</p>
              </div>
              <div>
                <p>출판사</p>
                <p>현대문학</p>
              </div>
              <div>
                <p>발간일</p>
                <p>2020.08.12</p>
              </div>
            </div>
          </div>
          <select className="voice" name="voice">
            <option value=''>장르 선택</option>
            <option value='성우1'>성우1</option>
            <option value='성우2'>성우2</option>
            <option value='성우3'>성우3</option>
          </select>
        </div>
      </div>
    </div>
  )
}