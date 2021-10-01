import React from 'react';
import UserInfo from '../component/UserInfo';
import UserLikeBook from '../component/UserLikeBook';
import UserReadBook from '../component/UserReadBook';

export default function UserProfile() {
    return (
      <>
        <UserInfo />
        <UserLikeBook />
        <UserReadBook />
      </>
    );
}