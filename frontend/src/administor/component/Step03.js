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
        for(var i = 0; i < e.target.files.length; i++){
            fd.append('imageFiles', e.target.files[i]);
        }
        console.log(e.target.files)
        console.log(imagefiles)
        // fd.append('imageFiles', imagefiles);
        dispatch(makeTextFile(fd, props.book.id))
        .then((res) =>{
            console.log(res);
            setTextFiles(res.payload.data + "\\text\\1.txt");
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
            setAudioFiles(res.payload.data + "\\sound\\1.mp3");
            isPlayer = 'visible';
            alert('오디오북이 생성되었습니다')
          })
          .catch((err) => {
              console.log(err)
              alert('오류가 발생했습니다')
          });
        // history.push({
        //     pathname: "/book",
        //     state: {book: props.book}
        // })
    }
    const handleTextFile = (e) => {
    //     let response = await fetch(textFiles);
    //     let blob = await response.blob();
    //     const target = new File([blob], '1.txt', {
    //         type: 'text'
    //     })
    //     console.log(target)
    //     var reader = new FileReader();
    //     reader.readAsText(target, "UTF-8");

    //     reader.onload = function(){
    //         setText(reader.result);
    //     }
    }
    let filename = 'PDF/JPEG 넣기';
    let returnFile = '텍스트 열기';
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
                        <div class="fileCard" onClick={handleTextFile}>
                            {text}
                            {/* <label for="text_file">{returnFile}</label>
                        <input type="file"
                            id="text_file" 
                            name="file"
                            style={{display:"none"}}
                            onChange={handlerTextFile}/> */}
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
            <audio src={audioFiles} controls className="player" style={{display: isPlayer}}></audio>
        </div>
    ); 
}
export default Step03;