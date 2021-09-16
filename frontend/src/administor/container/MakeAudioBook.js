import React, { useRef } from 'react';
import BG1 from '../../img/MakeAudioBG1.png'
import BG2 from '../../img/MakeAudioBG2.png'
import BG3 from '../../img/MakeAudioBG3.png'
import nextBtn from '../../img/nextBtn.png'
import prevBtn from '../../img/prevBtn.png'
import Step02 from '../component/Step02';
import Step01 from '../component/Step01'
import Step03 from '../component/Step03'

import MakeAudioBookModule from './MakeAudioBook.modules.scss'

const MakeAudioBook = () => {

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
        <Step01></Step01>
        <img src={BG1} alt="BG1"/>
      </section>

      <section>
        <h2>Step 2</h2>
        <Step02 />
        <img src={BG2} alt="BG2"/>
      </section>
      
      <section>
        <h2>Step 3</h2>
        <Step03></Step03>
        <img src={BG3} alt="BG3"/>
      </section>
    </div>
  );
}

export default MakeAudioBook;