import React, { useState } from 'react';
import BG1 from '../../img/MakeAudioBG1.png'
import BG2 from '../../img/MakeAudioBG2.png'
import BG3 from '../../img/MakeAudioBG3.png'
import nextBtn from '../../img/nextBtn.png'
import prevBtn from '../../img/prevBtn.png'
import MakeAudioBookModule from './MakeAudioBook.modules.scss'
import Step01 from '../component/Step01'

const MakeAudioBook = () => {

  const [page, pageState] = useState(0);

  let section = document.getElementsByTagName("section");
  let totalNum = 3;

  const prev = () => {
    if (page > 0) {
      pageState(page-1)
    }
    window.scrollTo({
      top: section[page].offsetTop,
      behavior: 'smooth',
    })
    console.log(page)
  }

  const next = () => {
    if (page < totalNum-1) {
      pageState(page+1)
    }
    window.scrollTo({
      top: section[page].offsetTop,
      behavior: 'smooth',
    })
    console.log(page)
    console.log(totalNum)
  }

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
        <div>
          
        </div>
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