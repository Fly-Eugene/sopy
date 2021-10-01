import React from 'react';
import './PageNum.modules.scss';

export default function PageNum( {currentPage, PageSize} ) {
    return (
      <div className="page">
        <p>{currentPage} | {PageSize}</p>
      </div>
    )
  }