import React, { useState } from 'react';
import { Link } from 'react-router-dom'
import { useDispatch } from 'react-redux';
import { login } from "../../store/actions/userActions";
import { useHistory } from 'react-router';
import axios from 'axios';

const LoginContent = (props) => {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const onEmailHandler = (e) => { setEmail(e.target.value)}
    const onPasswordHandler = (e) => { 
        setPassword(e.target.value)
    }
    const dispatch = useDispatch();
    const history = useHistory();
    const Login = (e) => {
        e.preventDefault();
        const body = {
            email: email,
            password: password
        }
        dispatch(login(body))
        .then((res) =>{
            alert('로그인이 완료되었습니다!');
            localStorage.setItem('jwt', res.payload.data.token)
            // axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('jwt')}`
            history.push("/");
            window.location.replace("/");
          })
          .catch((err) => {
              console.log(err)
              alert('아이디와 비밀번호를 확인해주세요')
          });
    }
    return (
        <div className="infobox">
            <h1>로그인</h1>
            <h4>소피의 책방에서 새로운 이야기를 들어보세요.</h4>
            <form onSubmit={Login}>
            <div className="userInfo">
            <input type='email' value={email} onChange={onEmailHandler} required placeholder='아이디'/>
            <input type='password' value={password} onChange={onPasswordHandler} required placeholder='비밀번호'/>
            </div>
            <button type="submit" className="signbtn" onSubmit={Login}>로그인</button>
            <p>계정이 없으신가요? <Link to='/signin'>회원가입하기</Link></p>
            </form>
        </div>
    ); 
    
}
export default LoginContent;
