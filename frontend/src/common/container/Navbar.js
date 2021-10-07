import React, { useState } from 'react';
import './Navbar.modules.scss'
import { Link } from 'react-router-dom'
import { useSelector } from 'react-redux';
function Navbar() {
  const data = useSelector(state => state.userReducer[0]);
  let admin = false;
  if(data && data.data){
    if(data.data.user && data.data.user.email == 'admin@sopy.com')
      admin = true;
  }
  let login = localStorage.getItem('jwt');

  const logout = (e) =>{
    localStorage.removeItem('jwt');
    alert('로그아웃 되었습니다.');
    login = localStorage.getItem('jwt');
    window.location.replace("/");
  }
    return (
      <div className="navbar">
        <h1><Link to='/'>소피의 책방</Link></h1>
        <div className="navbar-right">
          <p><Link to='/'>메인</Link></p>     
          <p><Link to='/find'>책 찾기</Link></p>
          {!admin && login && <p><Link to ='/user'>회원정보</Link></p>}
          {admin && login && <p><Link to ='/admin'>관리페이지</Link></p>}
          {login && <p><Link to ='/' onClick={logout}>로그아웃</Link></p>}
          {!login && <p><Link to='/login'>로그인</Link></p>}
        </div>
      </div>
    );
}

export default Navbar