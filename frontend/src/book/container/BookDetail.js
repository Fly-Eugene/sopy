import React, { useState } from 'react';
import BookDetailTitle from '../component/BookDetailTitle';
import BookDetailAudio from '../component/BookDetailAudio';
import BookDetailContent from '../component/BookDetailContent';
import BookDetailComment from '../component/BookDetailComment';
import { useSelector  } from 'react-redux';

export default function BookDetail(props) {
  const book = props.location.state.book;
  const [voice, setVoice] = useState('1');
  const getVoice = (param) => {
    setVoice(param);
  }
  return (
    <>
      <BookDetailTitle book={book} getVoice={getVoice}/>
      <BookDetailAudio book={book} voice={voice}/>
      <BookDetailContent content={book.introduce}/>
      <BookDetailComment id={book.id}/>
    </>
  )
}