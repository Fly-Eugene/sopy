import React, {useState, useEffect} from 'react';
import {Grid} from '@material-ui/core';
import sample from '../../img/SAMPLE_1.MP3';
import './Step03.modules.scss'
import { useHistory } from 'react-router';
import { useDispatch } from 'react-redux';
import { makeTextFile, makeAudioFile } from "../../store/actions/bookActions";
import createPalette from '@material-ui/core/styles/createPalette';

const Step03 = (props) => {
    const dispatch = useDispatch();

    const [files, setFiles] = useState([]);
    const [textFiles, setTextFiles] = useState([]);
    const [audioFiles, setAudioFiles] = useState('');
    const [text, setText] = useState('텍스트 열기');

    const handleFileOnChange = (e) => {
        e.preventDefault();
        setFiles(e.target.files)
        // let reader = new FileReader();
        // let target = e.target.files[0];
        // reader.onloadend = () =>{
        //     setFile(target)
        // }
        // reader.readAsDataURL(target);
        console.log(e.target.parentNode);
        e.target.parentNode.style.backgroundColor = '#FCDECF';
        var imagefiles = [];
        var fd = new FormData();
        console.log(e.target.files[0])
        for(var i = 0; i < e.target.files.length; i++){
            if(e.target.files[i].type =='application/pdf') fd.append('pdfFile', e.target.files[i]);
            if(e.target.files[i].type == 'image/png') fd.append('imageFiles', e.target.files[i]);
            if(e.target.files[i].type == 'image/jpeg') fd.append('imageFiles', e.target.files[i]);
        }
        console.log(e.target.files)
        console.log(imagefiles)
        dispatch(makeTextFile(fd, props.book.id))
        .then((res) =>{
            console.log(res);
          })
          .catch((err) => {
              console.log(err)
              alert('오류가 발생했습니다')
          });
    }
    const history = useHistory();

    const createAudioBook = (e) => {
        dispatch(makeAudioFile(props.book.id))
        .then((res) =>{
            console.log(res);
            alert('오디오북이 생성되었습니다')
            history.push({
                pathname: "/book",
                state: {book: props.book}
            })
          })
          .catch((err) => {
              console.log(err)
              alert('오류가 발생했습니다')
          });
    }
    
    let filename = 'PDF/JPEG 넣기';
    let isPlayer = 'none';
    if(files !== ''){
        // filename = files[0].name;
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
                            multiple="multiple"
                            id="pdf_file" 
                            name="file"
                            style={{display:"none"}}
                            onChange={handleFileOnChange}/>
                        </div>
                    </Grid>
                    <Grid item xs = {4}>
                        <div class="fileCard">
                            텍스트 열기
                        </div>
                    </Grid>
                    <Grid item xs = {4}>
                        <div class="fileCard"><label for="voice_file">성우 선택</label>
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
            <button className="createBtn" onClick={createAudioBook}>생성하기</button>
        </div>
    ); 
}
export default Step03;