import React , {useState, useEffect} from 'react';
import './ReadBookContent.modules.scss'
import bookCover from '../../img/book-cover.jpg'
import { useDispatch } from 'react-redux';
import { Grid } from '@material-ui/core';
import { getTextFile, getAudioFile, addBookmark, getBookmark, deleteBookmark } from '../../store/actions/bookActions';
import PageNum from '../../common/component/PageNum';
import { FaBookmark, FaRegBookmark } from 'react-icons/fa';
import { useHistory } from 'react-router';
import nextBtn from '../../img/nextBtn.png'
import prevBtn from '../../img/prevBtn.png'

export default function ReadBookContent(props) {
  const [bookmark, setBookmark] = useState(0);
  const [audioFile, SetAudioFile] = useState('');
  const [page, SetPage] = useState('');
  const [booklist, setBookList] = useState([]);

  const dispatch = useDispatch();
  const history = useHistory();
  useEffect(() => {
    getTextFileHandler();
    getAudioFileHandler();
    getBookMarkHandler();
  },[])
  const getTextFileHandler = e =>{
    setBookList([]);
    for(var i = 0; i < props.book.pageSize; i++){
      dispatch(getTextFile(props.book.id, i+1))
      .then((res) => {
        setBookList(booklist => [...booklist, res.payload.data]);
      })
      .catch((err) => console.log(err));
    }
  }
  const getAudioFileHandler = e => {
    dispatch(getAudioFile(props.book.id, props.voice))
    .then((res) => {
      SetAudioFile(res.payload.data);
    })
    .catch((err) => console.log(err));
  }
  const getBookMarkHandler = e =>{
    dispatch(getBookmark(props.book.id))
    .then((res) => {
      setBookmark(res.payload.data);
      if(res.payload.data == 0) SetPage(1);
      else SetPage(res.payload.data);
    })
    .catch((err) => console.log(err));
  }
  const bookmarkHanlder = e =>{
    if(bookmark != 0){
      dispatch(deleteBookmark(props.book.id))
      .then((res) => {
        setBookmark(0);
      })
      .catch((err) => console.log(err));
      return;
    }
    const data = {
      page: page,
      bookId: props.book.id
    }
    dispatch(addBookmark(data))
    .then((res) => {
    })
    .catch((err) => console.log(err));
    setBookmark(page)
  }
  const moveDetail = e =>{
    history.push({
      pathname: '/book',
      state: {book: props.book}
    })
  }
  const next = e =>{
    if(page >= booklist.length) return;
    SetPage(page+2);
  }
  const prev = e =>{
    if(page <= 1) return;
    SetPage(page-2);
  }
  return (
    <>
    <div className="read-container">
      <div className = "read-content">
        <Grid container>
            <Grid item xs={5} className="read-text">
              {booklist[page-1]}
            </Grid>
            <Grid item xs={1}><div className="vertical-line"></div></Grid>
            <Grid item xs={6} className="read-text">
              {page < booklist.length && booklist[page]}
            </Grid>
        </Grid>
      </div>
      <div className="read-page">
          <div className="prevBtn" onClick={prev}><img src={prevBtn}/></div>
          <PageNum PageSize = {page+1} currentPage = {page} />
          <div className="nextBtn" onClick={next}><img src={nextBtn}/></div>
      </div>
    </div>
      <div className="audio-container">
        <div className="audio-command">
          <p onClick={moveDetail} style={{cursor: "pointer"}}>상세페이지</p>
        </div>          
        <div className="audio-bar">
        <audio src={audioFile} controls className="player"></audio>
        </div>
        <div className="audio-bookmark">
          {bookmark == 0 && <FaRegBookmark onClick={bookmarkHanlder} style={{cursor:"pointer"}}/>}
          {bookmark != 0 && <FaBookmark onClick={bookmarkHanlder} style={{cursor:"pointer"}}/>}
        </div>
      </div>
    </>
  )
}