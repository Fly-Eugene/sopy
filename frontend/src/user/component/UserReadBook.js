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
import PageMove from '../../common/component/PageMove';
import PageNum from '../../common/component/PageNum';
import backsky from '../../img/back_skyblue.png';
import backred from '../../img/back_red.png';

export default function UserReadBook() {
  const [booklist, setBooklist] = useState([]);
  const imgArr = [backsky, underImgSrc, backred];
  const [bookarr, setbookarr] = useState([]);
  const [startIdx, setStart] = useState('');
  const [endIndx, setEnd] = useState('');
  const [page, setPage] = useState('');

  useEffect(() => {
    getBookList();
  },[])
  const dispatch = useDispatch();
  const history = useHistory();
  const getBookList = e => {
    dispatch(getRead())
    .then((res) => {
      setBooklist(res.payload.data)
      var arr = [];
      setPage(0);
      for(var i = 0; i < 3; i++){
          if(res.payload.data[i]) {
              setPage(1);
              arr.push(res.payload.data[i])
              setEnd(i)
          }
      }
      setbookarr(arr);
      setStart(0);
    })
    .catch((err) => console.log(err));
  }

  const prev = () => {
    if(startIdx <= 0) return;
    var arr = [];
    for(var i = startIdx - 3; i < startIdx; i++){
        arr.push(booklist[i]);
    }
    setbookarr(arr);
    setStart(startIdx-3);
    setEnd(startIdx-1);
    setPage(page-1);
  }
  const next = () => {
    if(endIndx >= booklist.length-1) return
    var arr = [];
    for(var i = startIdx + 3; i < startIdx + 6; i++){
        if(booklist.length > i){
            arr.push(booklist[i]);
            setEnd(i)
        }
    }
    setbookarr(arr);
    setStart(startIdx+3);
    setPage(page+1);
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
          <PageNum PageSize={Math.ceil(booklist.length / 3)} currentPage={page}/>
        </div>
        <div className="book-container">
        <Grid container className="book-container">
          {
            bookarr.map((book, index) => 
            <Grid key ={index} item xs={4} onClick={(e) => {moveDetail(book, e)}}>
              <Book underImgSrc={imgArr[index%3]} bookCover={book.bookImage.path + book.bookImage.imageName}/>
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