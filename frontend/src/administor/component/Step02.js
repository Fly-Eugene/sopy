import React, { useState, useCallback } from 'react';
import Step02Modules from './Step02.modules.scss'

const Step02 = (props) => {
  const sendData = () =>{
    props.getBookName(bookName);
    props.getGenre(genre);
    props.getAuthor(author);
    props.getEditor(editor);
    props.getDate(date);
    props.getPublisher(publisher);
    props.getContent(bookContent);
  }

  const [bookName, setBookName] = useState('');
  const [genre, setGenre] = useState('');
  const [author, setAuthor] = useState('');
  const [editor, setEditor] = useState('');
  const [date, setDate] = useState('');
  const [publisher, setPublisher] = useState('');
  // const [fileName, setFileName] = useState('');
  const [bookContent, setBookContent] = useState('');

  // const fileNameChange = (e) => {
  //   setFileName(`${e.target.files[0].name}`)
  // }

  
  const bookNameInput = useCallback((e) => setBookName(e.target.value), []);
  const bookAuthorInput = useCallback((e) => setAuthor(e.target.value), []);
  const bookEditorInput = useCallback((e) => setEditor(e.target.value), []);
  const bookDateInput = useCallback((e) => setDate(e.target.value), []);
  const bookPublisherInput = useCallback((e) => setPublisher(e.target.value), []);
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
          <h3>책 제목</h3>
          <h3>장르</h3>
          <h3>저자</h3>
          <h3>역자</h3>
          <h3>발간일</h3>
          <h3>출판사</h3>
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
          <input value={author} onChange={bookAuthorInput}/>
          <input value={editor} onChange={bookEditorInput}/>
          <input type='date' value={date} onChange={bookDateInput}/>
          <input value={publisher} onChange={bookPublisherInput}/>
          {/* <div className="file-box">
            <p>{fileName}</p>        
            <label htmlFor="ex-file">+</label>
            <input type="file" id="ex-file" onChange={fileNameChange}/>
          </div> */}
          <textarea cols="30" rows="8" value={bookContent} onChange={bookContentInput}/>
        </div>
      </div>
      <button onClick={sendData}>테스트</button>
    </div>
  )
}

export default Step02;