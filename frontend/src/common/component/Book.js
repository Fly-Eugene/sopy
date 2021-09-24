import React from 'react';
import './Book.modules.scss'

export default function Book( {underImgSrc, bookCover} ) {
  return (
    <div className="book-img">
      <img src={ bookCover } className="book-cover" alt="book-cover" />
      <img src={ underImgSrc } className="book-under" alt="book-under" />
    </div>
  )
}