import React from 'react';
import BookDetailAudio from '../component/BookDetailAudio';
import ReadBookContent from '../component/ReadBookContent';

export default function ReadBook(props) {
    const book = props.location.state.book;
    const voice = props.location.state.voice;
    return (
      <div>
        <ReadBookContent book={book} voice={voice}/>
      </div>
    );
}