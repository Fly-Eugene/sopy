import React, {useState, useEffect} from 'react';
import {Grid} from '@material-ui/core';
import sample from '../../img/SAMPLE_1.MP3';
import Step03Modules from './Step03.modules.scss'

const Step03 = () => {
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
    }
    let filename = 'PDF/JPEG 넣기';
    let returnFile = '텍스트 넣기';
    let isPlayer = 'none';
    if(file !== ''){
        filename = file.name;
    }
    return (
        <div className="step03">
            <h2 className="title">목소리로 변환해주세요</h2>
            <Grid container style={{marginTop: "13%"}}>
            <Grid item xs = {2}></Grid>
            <Grid item xs = {8}>
                <Grid container>
                    <Grid item xs = {4}>
                        <div class="fileCard"><label for="pdf_file">{filename}</label>
                        <input type="file"
                            id="pdf_file" 
                            name="file"
                            style={{display:"none"}}
                            onChange={handleFileOnChange}/>
                        </div>
                    </Grid>
                    <Grid item xs = {4}>
                        <div class="fileCard"><label for="text_file">{returnFile}</label>
                        <input type="file"
                            id="text_file" 
                            name="file"
                            style={{display:"none"}}
                            onChange={handleFileOnChange}/>
                        </div>
                    </Grid>
                    <Grid item xs = {4}>
                        <div class="fileCard"><label for="voice_file">성우 선택</label>
                        <audio src={sample} controls className="player" style={{display: isPlayer}}></audio>
                        <input type="file"
                            id="voice_file" 
                            name="file"
                            style={{display:"none"}}
                            onChange={handleFileOnChange}/>
                        </div>
                        
                    </Grid>
                </Grid>
            </Grid>
            <Grid item xs = {2}></Grid>
            </Grid>
            <button className="createBtn">생성하기</button>
        </div>
    ); 
}
export default Step03;