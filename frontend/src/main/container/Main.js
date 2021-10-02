import React from 'react';
import './Main.modules.scss'
// import {Wave} from './Mainwave.js';
import {WaveGroup} from './MainwaveGroup'
import SiriWave from 'siriwave'

class WaveApp {
  /* 생성자 */
  constructor() {
    /* 캔버스 엘리먼트 생성 */
    // this.canvas = document.createElement('canvas');
    this.canvas = document.querySelector(".wave")
  
    console.log(this.canvas)
    console.log(this.canvas_sub)
    this.ctx = this.canvas.getContext('2d');
  
    /* 현재 html 문서의 body에 캔버스 엘리먼트 추가하기 */
    // this.node = document.querySelector(".router-body")
  
    /* 웨이브 객체 생성 */
    this.wave = new WaveGroup();
  
  
    window.addEventListener('resize', this.resize.bind(this), {
      once: false,
      passive: false,
      capture: false,
    });
  
    /* 초기 사이즈를 기준으로 resize 함수 실행 */
    this.resize();
    requestAnimationFrame(this.animate.bind(this));
  }
  
  /* 사이즈가 변했을 때 실행될 콜백 */
  resize() {
    /* 레티나 디스플레이에서 올바른 화면을 보여주기 위해 설정 */
    this.stageWidth = document.body.clientWidth;
    this.stageHeight = document.body.clientHeight;
  
    /* 캔버스의 크기를 스테이지의 2배로 잡음 */
    this.canvas.width = this.stageWidth * 2;
    this.canvas.height = this.stageHeight * 2;
    this.ctx.scale(2, 2);
  
    /* 웨이브에도 리사이즈가 적용 되도록 설정 */
    this.wave.resize(this.stageWidth, this.stageHeight);
  }
  
  /* 애니메이션 관련 루틴 정의 */
  animate(t) {
    this.ctx.clearRect(0, 0, this.stageWidth, this.stageHeight);
    this.wave.draw(this.ctx);
    requestAnimationFrame(this.animate.bind(this));
  }
}


window.onload = () => {
  // new WaveApp();
  var siriWave = new SiriWave({
    container : document.querySelector(".siri-container"),
    width: 1000,
    height: 500,
    style: 'ios9'
  })
};

const Main = () => {

  return (
    <div className="mainbox">
      <h1 className="mainIntro">
        Sopy, Sound of Paper for you
      </h1>
      <div className="siri-container"></div>
      {/* <canvas className="wave"></canvas> */}
    </div>
  )

}
  
export default Main;

