import React from "react";
import './BookDetailAudio.modules.scss'
import { FaPlay, FaStop, FaRegBookmark } from 'react-icons/fa'

export default function BookDetailAudio() {
  return (
    <div className="audio-container">
      <div className="audio-command">
        <p>텍스트랑 같이 보기</p>
        <FaPlay />
        <FaStop />
      </div>
      <div className="audio-bar">
        
      </div>
      <div className="audio-bookmark">
        <FaRegBookmark />
      </div>
    </div>
  )
}