import React, { useState } from 'react';
import coverImg from '../../img/cover.png'
import './Step01.modules.scss'

const Step01 = (props) => {
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
        props.getImageFile(target)
    }
    let cover = null;
    if(file !== ''){
        cover = <img className='cover' src={preview} alt="book-cover"></img>
    }else{
        cover = <img className='cover' src={coverImg} alt="book-cover"></img>
    }
    return (
        <div className="step01">
            <h2 className="title">책 표지를 선택해주세요</h2>
            {cover}
            <div className="fileBtn">
                <label for="ex_file" className="fileBtnLabel">사진 첨부</label>
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
export default Step01;
