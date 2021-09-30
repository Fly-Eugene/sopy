import React from "react";
import './BookDetailAudio.modules.scss'
import { FaPlay, FaStop, FaRegBookmark } from 'react-icons/fa'
import sample from '../../img/SAMPLE_1.MP3';

export default function BookDetailAudio() {
  return (
    <div className="audio-container">
      <div className="audio-command">
        <p>텍스트랑 같이 보기</p>
        {/* <FaPlay />
        <FaStop /> */}
      </div>
      <div className="audio-bar">
      <audio src="https://sopy.s3.ap-northeast-2.amazonaws.com/test.wav" controls className="player"></audio>
      </div>
      <div className="audio-bookmark">
        <FaRegBookmark />
      </div>
    </div>
  )
}