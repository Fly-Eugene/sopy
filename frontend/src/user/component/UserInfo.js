import React, { useEffect, useState } from 'react';
import { MdSettings } from 'react-icons/md'
import { useDispatch, useSelector } from 'react-redux';
import profile from '../../img/profile.png'
import { Link } from 'react-router-dom'

import './UserInfo.modules.scss'
import { getRead, getLikes } from '../../store/actions/bookActions';

export default function UserInfo() {
  const [booklist, setBooklist] = useState([]);
  const [likeBooks, setLikeBooks] = useState([]);
  useEffect(() => {
    getBookList();
    getLikeBookHandler();
  },[])
  const dispatch = useDispatch();
  const getBookList = e => {
    dispatch(getRead())
    .then((res) => {
      setBooklist(res.payload.data)
    })
    .catch((err) => console.log(err));
  }
  const getLikeBookHandler = e => {
    console.log("??")
    dispatch(getLikes())
    .then((res) => {
      setLikeBooks(res.payload.data.book)
    })
    .catch((err) => console.log(err));
  }

  const user = useSelector(state => state.userReducer[0].data.user);
  return (
    <div className="info-container">
      <div className="info-left">
        <div className="info-left-top">
          <MdSettings />
          <div className="profile-img">
            <img src={ user.userImage.path + user.userImage.imageName} alt="profile" />
          </div>
          <div className="profile-name">
            <p>어서오세요</p>
            <p>{user.username} 님</p>
          </div>
        </div>
        <div className="info-left-bottom">
          <div className="profile-info-name">
            <p>Email</p>
            <p>Group</p>
          </div>
          <div className="profile-info-content">
            <p>{user.email}</p>
            <p>{user.department}</p>
          </div>
        </div>
      </div>
      <div className="info-right">
        <div className="info-right-top">
          <div className="info-right-top-container">
            {user.email != 'admin@sopy.com' && <p>좋아하는 책</p> }
            {user.email != 'admin@sopy.com' && <p>총 {likeBooks.length}권</p> }
            {user.email == 'admin@sopy.com' && <p><Link to ='/makeaudio'>오디오북 만들기</Link></p>}
          </div>
        </div>
        <div className="info-right-bottom">
          <div className="info-right-bottom-container">
            {user.email != 'admin@sopy.com' && <p>내가 읽은 책</p>}
            {user.email != 'admin@sopy.com' && <p>총 {booklist.length}권</p>}
            {user.email == 'admin@sopy.com' && <p><Link to ='/find'>오디오북 찾기</Link></p>}
          </div>
        </div>
      </div>
    </div>
  );
}