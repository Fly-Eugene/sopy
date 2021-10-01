import React, { useState } from 'react';
import background from '../../img/wave.png';
import './LoginImg.modules.scss';

const LoginImg = (props) => {
    return (
        <div className="LoginImgContainer">
            <h1>소피의</h1>
            <h1>책방</h1>
            <img src={background} className="imgBack"></img>
        </div>
    ); 
    
}
export default LoginImg;
