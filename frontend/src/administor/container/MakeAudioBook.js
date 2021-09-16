import React, { useState } from 'react';
import BG1 from '../../img/MakeAudioBG1.png'
import BG2 from '../../img/MakeAudioBG2.png'
import BG3 from '../../img/MakeAudioBG3.png'
import nextBtn from '../../img/nextBtn.png'
import prevBtn from '../../img/prevBtn.png'
import Step02 from '../component/Step02';

import MakeAudioBookModule from './MakeAudioBook.modules.scss'

const MakeAudioBook = () => {

  // const [page, pageState] = useState(0);
  let page = 0;
  let section = document.getElementsByTagName("section");
  let totalNum = 3;

  const prev = () => {
    if (page > 0) {
      page--;
    }
    window.scrollTo({
      top: section[page].offsetTop,
      behavior: 'smooth',
    })
    console.log(page)
  }

  const next = () => {
    if (page < totalNum-1) {
      page++;
    }
    window.scrollTo({
      top: section[page].offsetTop,
      behavior: 'smooth',
    })
    console.log(page)
    console.log(totalNum)
  }
  
  window.addEventListener("scroll", function(event){
    var scroll = this.scrollY;
    for(var i=0; i<totalNum; i++){
      if(scroll > section[i].offsetTop - window.outerHeight/3  && scroll < section[i].offsetTop - window.outerHeight/3 + section[i].offsetHeight){
        page = i;
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
        <div>
        </div>
        <img src={BG1} alt="BG1"/>
      </section>

      <section>
        <h2>Step 2</h2>
        <Step02 />
        <img src={BG2} alt="BG2"/>
      </section>
      
      <section>
        <h2>Step 3</h2>
        <div>
          
        </div>
        <img src={BG3} alt="BG3"/>
      </section>
    </div>
  );
}

export default MakeAudioBook;