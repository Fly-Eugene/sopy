import React from 'react';
import LoginImg from '../component/LoginImg'
import LoginContent from '../component/LoginContent'
import './SignIn.modules.scss';
import {Grid} from '@material-ui/core';

export default function Login() {
    return (
      <Grid container className="login-container">
        <Grid item xs = {7}><LoginImg/></Grid>
        <Grid item xs = {5}><LoginContent/></Grid>
      </Grid>
    );
}