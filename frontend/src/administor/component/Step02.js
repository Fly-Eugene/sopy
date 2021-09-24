import React, { useState, useCallback } from 'react';
import './Step02.modules.scss'

const Step02 = () => {
  const [bookName, setBookName] = useState('');
  const [genre, setGenre] = useState('');
  const [fileName, setFileName] = useState('');
  const [bookContent, setBookContent] = useState('');

  const fileNameChange = (e) => {
    setFileName(`${e.target.files[0].name}`)
  }

  const bookNameInput = useCallback((e) => setBookName(e.target.value), []);
  const bookContentInput = useCallback((e) => setBookContent(e.target.value), []);

  const genreInput = (e) => {
    let genreIndex = e.target.options.selectedIndex
    setGenre(`${e.target.options[genreIndex].value}`)
  }

  return (
    <div className="step02-container">
      <h2 className="step02-title">책 정보를 입력해주세요.</h2>
      <div className="step02-form-container">
        <div className="step02-form-container-left">
          <h3>책 이름</h3>
          <h3>장르</h3>
          <h3>파일첨부</h3>
          <h3>책 소개</h3>
        </div>
        <div className="step02-form-container-right">
          <input value={bookName} onChange={bookNameInput}/>
          <select name="genre" onChange={genreInput}>
            <option value=''>장르 선택</option>
            <option value='호러'>호러</option>
            <option value='판타지'>판타지</option>
            <option value='로맨스'>로맨스</option>
          </select>
          <div className="file-box">
            <p>{fileName}</p>        
            <label htmlFor="ex-file">+</label>
            <input type="file" id="ex-file" onChange={fileNameChange}/>
          </div>
          <textarea cols="40" rows="8" value={bookContent} onChange={bookContentInput}/>
        </div>
      </div>
    </div>
  )
}

export default Step02;