import React from 'react';
import BookDetailTitle from '../component/BookDetailTitle';
import BookDetailAudio from '../component/BookDetailAudio';
import BookDetailContent from '../component/BookDetailContent';
import BookDetailComment from '../component/BookDetailComment';


export default function BookDetail() {
  return (
    <>
      <BookDetailTitle />
      <BookDetailAudio />
      <BookDetailContent />
      <BookDetailComment />
    </>
  )
}