import React, { useEffect, useState } from "react";
import './BookDetailAudio.modules.scss'
import { FaPlay, FaStop, FaRegBookmark } from 'react-icons/fa'
import sample from '../../img/SAMPLE_1.MP3';
import { useDispatch } from "react-redux";
import { getAudioFile } from "../../store/actions/bookActions";
import { useHistory } from "react-router";

export default function BookDetailAudio(props) {
  const [filesrc, SetFilesrc] = useState('');
  useEffect(() => {
    getAudioFileHandler();
  })
  const dispatch = useDispatch();
  const history = useHistory();
  const getAudioFileHandler = e => {
    dispatch(getAudioFile(props.book.id, props.voice))
    .then((res) => {
      SetFilesrc(res.payload.data);
    })
    .catch((err) => console.log(err))
  }

  const moveRead = e =>{
    history.push({
      pathname: '/read',
      state: {
        book: props.book,
        voice: props.voice}
    })
  }
  return (
    <div className="audio-container">
      <div className="audio-command">
        <p onClick={moveRead} style={{cursor: "pointer"}}>텍스트랑 같이 보기</p>
      </div>
      <div className="audio-bar">
      <audio src={filesrc} controls className="player"></audio>
      </div>
    </div>
  )
}