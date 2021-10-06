import React, { useEffect, useState } from 'react';
import '../../user/component/UserLikeBook.modules.scss'
import Book from '../component/Book';
import PageNum from '../component/PageNum';
import underImgSrc from '../../img/book_under.png';
import backyellow from '../../img/back_yellow.png';
import backpink from '../../img/back_pink.png';
import bookCover from '../../img/book-cover.jpg'
import nextBtn from '../../img/nextBtn.png'
import prevBtn from '../../img/prevBtn.png'
import { useDispatch } from 'react-redux';
import { getLikes } from '../../store/actions/bookActions';
import { useHistory } from 'react-router';
import { Grid } from '@material-ui/core';

export default function PageMove({booklist, title}) {
  const history = useHistory();
  const imgArr = [underImgSrc, backyellow, backpink];
  const [bookarr, setbookarr] = useState([]);
  const [startIdx, setStart] = useState('');
  const [endIndx, setEnd] = useState('');
  const [page, setPage] = useState('');
  useEffect(()=>{
      load();
  },[])
  const load = e => {
      var arr = [];
      for(var i = 0; i < 3; i++){
          if(booklist[i]) {
              arr.push(booklist[i])
              setEnd(i)
          }
      }
      setbookarr(arr);
      setPage(1);
      setStart(0);
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
    <div className="like-book-container">
      <div className="like-book-inner">
      <div className="book-header">
        <span>{title}</span>
        <PageNum PageSize={Math.floor(booklist.length / 3) + 1} currentPage={page}/>
      </div>
        <div className="book-container">
        <Grid container>
          {
            bookarr.map((book, index) => 
            <Grid key = {index} item xs={4} onClick={(e) => {moveDetail(book, e)}} style={{cursor:'pointer'}}>
              <Book underImgSrc={imgArr[Math.floor(Math.random() * 3)]} bookCover={book.bookImage.path + book.bookImage.imageName}/>
            </Grid>
            )
            }
        </Grid>        
        
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