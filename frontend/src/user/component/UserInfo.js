import React from 'react';
import { MdSettings } from 'react-icons/md'
import { useSelector } from 'react-redux';
import profile from '../../img/profile.png'

import './UserInfo.modules.scss'

export default function UserInfo() {
  const user = useSelector(state => state.userReducer[0].data.user);
  console.log(user);
  return (
    <div className="info-container">
      <div className="info-left">
        <div className="info-left-top">
          <MdSettings />
          <div className="profile-img">
            <img src={ user.userImage.path + user.userImage.imageName} alt="profile" />
          </div>
          <div className="profile-name">
            <p>어서요세요</p>
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
            <p>내가 읽은 책</p>
            <p>총 54권</p>
          </div>
        </div>
        <div className="info-right-bottom">
          <div className="info-right-bottom-container">
            <p>당신의 목소리를 들려주세요</p>
            <p>녹음하기</p>
          </div>
        </div>
      </div>
    </div>
  );
}