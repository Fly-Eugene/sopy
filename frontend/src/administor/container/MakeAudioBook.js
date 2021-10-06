import React, { useRef, useState } from 'react';
import BG1 from '../../img/MakeAudioBG1.png'
import BG2 from '../../img/MakeAudioBG2.png'
import BG3 from '../../img/MakeAudioBG3.png'
import nextBtn from '../../img/nextBtn.png'
import prevBtn from '../../img/prevBtn.png'
import Step02 from '../component/Step02';
import Step01 from '../component/Step01'
import Step03 from '../component/Step03'

import './MakeAudioBook.modules.scss'
import { useDispatch } from 'react-redux';
import { makeBook } from "../../store/actions/bookActions";
import axios from 'axios';


const MakeAudioBook = () => {
  const [title, setTitle] = useState('')
  const [imageFile, setImageFile] = useState('')
  const [introduce, setContent] = useState('')
  const [genre, setGenre] = useState('')
  const [author, setAuthor] = useState('')
  const [translator, setEditor] = useState('')
  const [publisher, setPublisher] = useState('')
  const [publishedDate, setDate] = useState('')
  const [book, setBook] = useState({})

  const getBookName = (bookName) =>{
    setTitle(bookName);
  }
  const getImageFile = (img) => {
    setImageFile(img);
  }
  const getContent = (content) => {
    setContent(content);
  }
  const getGenre = (type) => {
    setGenre(type);
  }
  const getAuthor = (data) => {
    setAuthor(data)
  }
  const getEditor = (data) => {
    setEditor(data)
  }
  const getPublisher = (data) => {
    setPublisher(data)
  }
  const getDate = (data) => {
    setDate(data)
  }
  const dispatch = useDispatch();

  const onSubmitHandler = (e) => {
    if(title === '' || introduce === '' || genre === '' || author === '' || translator == '' || publisher == '' || publishedDate == ''){ 
      alert('정보를 모두 입력해주세요')
      return false
    }
    var fd = new FormData();
    fd.append('imageFile', imageFile);
    fd.append('title', title);
    fd.append('introduce', introduce);
    fd.append('genre', genre);
    fd.append('author', author);
    fd.append('translator', translator);
    fd.append('publisher', publisher);
    fd.append('publishedDate', publishedDate);
    dispatch(makeBook(fd))
    .then((res) =>{
      setBook(res.payload.data);
    })
    .catch((err) => {
        console.log(err)
        alert('오류가 발생했습니다')
    });
    return true
  }
  const page = useRef(0);
  const totalNum = useRef(3);
  const section = document.getElementsByTagName("section");

  const prev = () => {
    if (page.current > 0) {
      page.current--;
    }
    window.scrollTo({
      top: section[page.current].offsetTop + 100,
      behavior: 'smooth',
    })
  }

  const next = () => {
    if(page.current === 1){
      if(!onSubmitHandler()) return
    }
    if (page.current < totalNum.current-1) {
      page.current++;
    }
    window.scrollTo({
      top: section[page.current].offsetTop + 100,
      behavior: 'smooth',
    })
  }
  
  window.addEventListener("scroll", function(event){
    var scroll = this.scrollY;
    for(var i=0; i<totalNum; i++){
      if(scroll > section[i].offsetTop - window.outerHeight/3  && scroll < section[i].offsetTop - window.outerHeight/3 + section[i].offsetHeight){
        page.current = i;
        break;
      }
    }
  });

  return (
    <div className='bookmake-content'>
      <div className="page-navi">
        <div className="prevBtn" onClick={prev}><img src={prevBtn} alt="prevBtn"/></div>
        <div className="nextBtn" onClick={next}><img src={nextBtn} alt="nextBtn"/></div>
      </div>

      <section>
        <h2>Step 1</h2>
        <Step01 getImageFile={getImageFile}></Step01>
        <img src={BG1} alt="BG1"/>
      </section>

      <section>
        <h2>Step 2</h2>
        <Step02 
        getBookName={getBookName} 
        getImageFile={getImageFile} 
        getContent={getContent}
        getGenre={getGenre}
        getAuthor={getAuthor}
        getEditor={getEditor}
        getPublisher={getPublisher}
        getDate={getDate}
        />
        <img src={BG2} alt="BG2"/>
      </section>
      
      <section>
        <h2>Step 3</h2>
        <Step03 book={book}></Step03>
        <img src={BG3} alt="BG3"/>
      </section>
    </div>
  );
}

export default MakeAudioBook;