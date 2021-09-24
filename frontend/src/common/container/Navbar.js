import React from 'react';
import './Navbar.modules.scss'
import { Link } from 'react-router-dom'
function Navbar() {
    return (
      <div className="navbar">
        <h1>소피의 책방</h1>
        <div className="navbar-right">
          <p><Link to='/'>개요</Link></p>     
          <p><Link to='/find'>책 찾기</Link></p>
          <p><Link to='/login'>로그인</Link></p>
        </div>
      </div>
    );
}

export default Navbar