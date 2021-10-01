import React from 'react';
import BookDetailAudio from '../component/BookDetailAudio';
import ReadBookContent from '../component/ReadBookContent';

export default function ReadBook(props) {
    const book = props.location.state.book;
    return (
      <div>
        <ReadBookContent book={book}/>
      </div>
    );
}