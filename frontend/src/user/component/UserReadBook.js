import React, { useEffect, useState } from 'react';
import Book from '../../common/component/Book'
import underImgSrc from '../../img/book_under.png';
import bookCover from '../../img/book-cover.jpg'
import nextBtn from '../../img/nextBtn.png'
import prevBtn from '../../img/prevBtn.png'
import './UserReadBook.modules.scss'
import { useDispatch } from 'react-redux';
import { useHistory } from 'react-router';
import { getRead } from '../../store/actions/bookActions';
import { Grid } from '@material-ui/core';

export default function UserReadBook() {
  const [booklist, setBooklist] = useState([]);
  useEffect(() => {
    getBookList();
  },[])
  const dispatch = useDispatch();
  const history = useHistory();
  const getBookList = e => {
    dispatch(getRead())
    .then((res) => {
      console.log(res.payload.data)
      setBooklist(res.payload.data)
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
    <div className="read-book-container">
      <div className="read-book-inner">
        <div className="book-header">
          <span>내가 읽은 책</span>
          <span>1 / 20</span>
        </div>
        <div className="book-container">
        <Grid container className="book-container">
          {
            booklist.map((book) => 
            <Grid item xs={4} onClick={(e) => {moveDetail(book, e)}}>
              <Book underImgSrc={underImgSrc} bookCover={book.bookImage.path + book.bookImage.imageName}/>
            </Grid>
            )
            }
        </Grid>
        </div>
      </div>
      <div className="read-botton-direction">
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