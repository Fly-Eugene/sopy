import React, { useState } from 'react';
import './SignInContent.modules.scss'

import { useDispatch } from 'react-redux';
import { join } from "../../store/actions/userActions";
import { useHistory } from 'react-router';

const SignInContent = (props) => {
    const imageFile = props.imageFile;
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [pwdcheck, setPwdcheck] = useState('')
    const [group, setGroup] = useState('')
    const [name, setName] = useState('')
    const [age, setAge] = useState('')
    const [pwdError, setPwdError] = useState(false)
    const [pwdcheckError, setPwdcheckError] = useState(false)
    const [agree, setAgree] = useState(false)

    const onEmailHandler = (e) => { setEmail(e.target.value)}
    const onPasswordHandler = (e) => { 
        setPassword(e.target.value)
        if(e.target.value.length < 6) setPwdError(true)
        else setPwdError(false)
    }
    const onPwdcheckHandler = (e) => {
        if(password !== e.target.value) setPwdcheckError(true)
        else setPwdcheckError(false)
        setPwdcheck(e.target.value)}
    const onGroupHandler = (e) => { setGroup(e.target.value)}
    const onNameHandler = (e) => { setName(e.target.value)}
    const onAgeHandler = (e) => { setAge(e.target.value)}
    const onCheckboxHandler = (e) => { setAgree(e.target.checked) }

    const dispatch = useDispatch();
    const history = useHistory();

    const signIn = (e) => {
        e.preventDefault();
        if(password != pwdcheck){
            return alert('비밀번호와 비밀번호 확인은 같아야 합니다.')
        }
        if(!agree){
            return alert('이용약관에 동의해주세요');
        }
        
         var fd = new FormData();
          fd.append('userImage', imageFile);
          fd.append('email', email);
          fd.append('password', password);
          fd.append('department', group);
          fd.append('username', name);
          fd.append('age', age);
          dispatch(join(fd))
          .then((res) =>{
            alert('회원가입이 완료되었습니다!');
            history.push("/login");
          })
          .catch((err) => {
              console.log(err)
              alert('오류가 발생했습니다')
          });
    }
    let pwdErrormsg = '';
    let pwdcheckErrormsg = '';
    if(pwdError){
        pwdErrormsg = <p style={{color:'red', fontSize: '0.8rem', margin:'0'}}>비밀번호를 6자리 이상 입력해주세요</p>
    }
    else pwdErrormsg = ''
    if(pwdcheckError){
        pwdcheckErrormsg = <p style={{color:'red', fontSize: '0.8rem', margin:'0'}}>입력한 비밀번호와 일치하지 않습니다</p>
    }
    else pwdcheckErrormsg = ''
    return (
        <div className="infobox">
            <h1>회원가입</h1>
            <h4>회원가입을 위해 아래의 정보를 입력해주세요</h4>
            <form onSubmit={signIn}>
            <div className="userInfo">
            <input type='email' value={email} onChange={onEmailHandler} required placeholder='이메일'/>
            <input type='password' value={password} onChange={onPasswordHandler} required placeholder='비밀번호(6자리 이상)'/>
            {pwdErrormsg}
            <input className='pwdbox' type='password' value={pwdcheck} onChange={onPwdcheckHandler} required placeholder='비밀번호 확인'/>
            {pwdcheckErrormsg}
            <input value={name} onChange={onNameHandler} required placeholder='이름'/>
            <input type='number' value={age} onChange={onAgeHandler} required placeholder='나이'/>
            <input value={group} onChange={onGroupHandler} required placeholder='소속'/>
            </div>
            <div>
            <label
                ><input
                  type="checkbox"
                  class="checkword"
                  name="agreement"
                  onChange={onCheckboxHandler}
                />사이트의 이용방침에 동의합니다
            </label>
            </div>
            <button type="submit" className="signbtn" onSubmit={signIn}>회원가입</button>
            </form>
        </div>
    ); 
    
}
export default SignInContent;
