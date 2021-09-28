import React from 'react';
import './BookDetailContent.modules.scss'

export default function BookDetailContent(props) {
  return (
    <div className="content-container">
      <h2>책 소개</h2>
      <div className="content">
        <div className="text">
        {props.content}
        </div>
      </div>
    </div>
  )
}