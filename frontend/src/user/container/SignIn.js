import React, {useState} from 'react';
import SignInContent from '../component/SignInContent';
import SignInImg from '../component/SignInImg';
import './SignIn.modules.scss';
import {Grid} from '@material-ui/core';

export default function SignIn() {
  const [imageFile, setImageFile] = useState('')

  const getImageFile = (img) => {
    setImageFile(img);
  }

    return (
      <div className="login-container">
      <Grid container>
        <Grid item xs = {7}><SignInImg getImageFile={getImageFile}/></Grid>
        <Grid item xs = {5}><SignInContent imageFile={imageFile}/></Grid>
      </Grid>
      </div>
    );
}