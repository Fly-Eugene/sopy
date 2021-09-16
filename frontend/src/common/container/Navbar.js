import React from 'react';
import NavbarModules from './Navbar.modules.scss'

function Navbar() {
    return (
      <div className="navbar">
        <h1>소피의 책방</h1>
        <div className="navbar-right">
          <p>개요</p>
          <p>책 찾기</p>
          <p>로그인</p>
        </div>
      </div>
    );
}

export default Navbar