import React, { useEffect, useState } from 'react';
import './UserLikeBook.modules.scss'
import Book from '../../common/component/Book'
import underImgSrc from '../../img/book_under.png';
import bookCover from '../../img/book-cover.jpg'
import nextBtn from '../../img/nextBtn.png'
import prevBtn from '../../img/prevBtn.png'
import { useDispatch } from 'react-redux';
import { getLikes } from '../../store/actions/bookActions';
import { useHistory } from 'react-router';
import { Grid } from '@material-ui/core';

export default function UserLikeBook() {
  const [booklist, setBooklist] = useState([]);
  useEffect(() => {
    getBookList();
  },[])
  const dispatch = useDispatch();
  const history = useHistory();
  const getBookList = e => {
    dispatch(getLikes())
    .then((res) => {
      console.log(res.payload.data.book)
      setBooklist(res.payload.data.book)
    })
    .catch((err) => console.log(err));
  }
  const prev = () => {
    return 
  }
  const next = () => {
    return
  }
  const moveDetail = (params, e) =>{
    history.push({
        pathname: "/book",
        state: {book: params}
    });
}
  return (
    <div className="like-book-container">
      <div className="like-book-inner">
        <div className="book-header">
          <span>좋아하는 책</span>
          <span>1 / 20</span>
        </div>
        <div className="book-container">
          {
            booklist.map((book) => {
              <Grid item xs={4} onClick={(e) => {moveDetail(book, e)}}>
                <Book underImgSrc={underImgSrc} bookCover={book.bookImage.path + book.bookImage.imageName}/>      
              </Grid>
            })
          }
        </div>
      </div>
      <div className="like-botton-direction">
        <div className="prevBtn" onClick={prev}>
          <img src={prevBtn} alt="prevBtn"/>
        </div>
        <div className="nextBtn" onClick={next}>
          <img src={nextBtn} alt="nextBtn"/>
        </div>
      </div>
    </div>
  );
}