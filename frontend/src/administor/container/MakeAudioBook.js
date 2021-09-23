import React, { useRef, useState } from 'react';
import BG1 from '../../img/MakeAudioBG1.png'
import BG2 from '../../img/MakeAudioBG2.png'
import BG3 from '../../img/MakeAudioBG3.png'
import nextBtn from '../../img/nextBtn.png'
import prevBtn from '../../img/prevBtn.png'
import Step02 from '../component/Step02';
import Step01 from '../component/Step01'
import Step03 from '../component/Step03'

import MakeAudioBookModule from './MakeAudioBook.modules.scss'
import { useDispatch } from 'react-redux';
import { makeBook } from "../../store/actions/bookActions";
import axios from 'axios';


const MakeAudioBook = () => {
  const [title, setTitle] = useState('')
  const [imageFile, setImageFile] = useState(0)
  const [introduce, setContent] = useState(0)
  const [genre, setGenre] = useState(0)
  const [author, setAuthor] = useState(0)
  const [translator, setEditor] = useState(0)
  const [publisher, setPublisher] = useState(0)
  const [publishedDate, setDate] = useState(0)

  const getBookName = (bookName) =>{
    console.log(bookName)
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
    e.preventDefault();
    let body = {
      imageFile: imageFile,
      title : title,
      introduce : introduce,
      genre : genre,
      author : author,
      translator : translator,
      publisher : publisher,
      publishedDate : publishedDate
    };
    dispatch(makeBook(body));
  }
  const page = useRef(0);
  const totalNum = useRef(3);
  const section = document.getElementsByTagName("section");

  const prev = () => {
    if (page.current > 0) {
      page.current--;
    }
    window.scrollTo({
      top: section[page.current].offsetTop,
      behavior: 'smooth',
    })
  }

  const next = () => {
    if (page.current < totalNum.current-1) {
      page.current++;
    }
    window.scrollTo({
      top: section[page.current].offsetTop,
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
    <div className='content'>
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
        <Step03></Step03>
        <img src={BG3} alt="BG3"/>
      </section>

      <button onClick={onSubmitHandler}>확인용</button>
    </div>
  );
}

export default MakeAudioBook;