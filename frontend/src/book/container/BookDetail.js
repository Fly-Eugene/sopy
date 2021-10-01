import React from 'react';
import BookDetailTitle from '../component/BookDetailTitle';
import BookDetailAudio from '../component/BookDetailAudio';
import BookDetailContent from '../component/BookDetailContent';
import BookDetailComment from '../component/BookDetailComment';
import { useSelector  } from 'react-redux';

export default function BookDetail(props) {
  console.log(props.location.state.book)
  const book = props.location.state.book;
  return (
    <>
      <BookDetailTitle book={book}/>
      <BookDetailAudio book={book}/>
      <BookDetailContent content={book.introduce}/>
      <BookDetailComment id={book.id}/>
    </>
  )
}