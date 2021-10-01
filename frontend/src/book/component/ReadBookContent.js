import React , {useState, useEffect} from 'react';
import './ReadBookContent.modules.scss'
import bookCover from '../../img/book-cover.jpg'
import { useDispatch } from 'react-redux';
import { Grid } from '@material-ui/core';
import { getTextFile, getAudioFile } from '../../store/actions/bookActions';
import PageNum from '../../common/component/PageNum';
import { FaRegBookmark } from 'react-icons/fa';
import { useHistory } from 'react-router';

export default function ReadBookContent(props) {
  const [audioFile, SetAudioFile] = useState('');
  const dispatch = useDispatch();
  const history = useHistory();
  useEffect(() => {
    getTextFileHandler();
    getAudioFileHandler();
  },[])
  const getTextFileHandler = e =>{
    dispatch(getTextFile(props.book.id,1))
    .then((res) => {
      console.log(res.payload.data);
      // readTextFile(res.payload.data);
    })
    .catch((err) => console.log(err));
  }
  const getAudioFileHandler = e => {
    dispatch(getAudioFile(props.book.id,1))
    .then((res) => {
      console.log(res.payload.data);
      SetAudioFile(res.payload.data);
    })
    .catch((err) => console.log(err));
  }

  const readTextFile = url => {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", url, true);
    xhr.responseType = "blob";
    xhr.onload = () =>{
      var reader = new FileReader();
      reader.readAsDataURL(xhr.response);
      reader.onload = e => {
        console.log(e.target.result);
      }
    }
    xhr.send();
  }
  const moveDetail = e =>{
    history.push({
      pathname: '/book',
      state: {book: props.book}
    })
  }
  return (
    <>
    <div className="read-container">
      <div className = "read-content">
        <Grid container>
            <Grid item xs={5}></Grid>
            <Grid item xs={2}><div className="vertical-line"></div></Grid>
            <Grid item xs={5}></Grid>
        </Grid>
      </div>
      <div className="read-page">
          <PageNum PageSize = {1} currentPage = {1} />
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
          <FaRegBookmark />
        </div>
      </div>
    </>
  )
}