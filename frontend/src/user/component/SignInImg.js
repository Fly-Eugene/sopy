import React, { useState } from 'react';
import './SignInImg.modules.scss'
import background from '../../img/book_under.png';
import profile from '../../img/userprofile.png';

const SignInImg = (props) => {
    const [file, setFile] = useState('');
    const [preview, setPreview] = useState('');
    const handleFileOnChange = (e) => {
        e.preventDefault();
        let reader = new FileReader();
        let target = e.target.files[0];
        reader.onloadend = () =>{
            setFile(target)
            setPreview(reader.result);
        }
        reader.readAsDataURL(target);
        props.getImageFile(target);
    }
    let profileImg = null;
    if(file !== ''){
        profileImg = <img className='profileImg' src={preview} alt="profileImg"></img>
    }else{
        profileImg = <img className='profileImg' src={profile} alt="profileImg"></img>
    }
    return (
        <div className="signImgContainer">
            <h1>소피의 책방</h1>
            <img src={background} className="imgBack"></img>
            <div className="imgBox">
            {profileImg}
            </div>
            <div className="fileBtn">
                <label for="ex_file" className="fileBtnLabel">프로필 변경</label>
                <input type="file"
                    id="ex_file" 
                    name="file"
                    accept='image/jpg, image/png, image/jpeg, image/gif' 
                    style={{display:"none"}}
                    onChange={handleFileOnChange}/>
            </div>
        </div>
    ); 
    
}
export default SignInImg;
