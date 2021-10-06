import React, {useState, useEffect} from 'react';
import {Grid} from '@material-ui/core';
import sample from '../../img/SAMPLE_1.MP3';
import './Step03.modules.scss'
import { useHistory } from 'react-router';
import { useDispatch } from 'react-redux';
import { makeTextFile, makeAudioFile } from "../../store/actions/bookActions";
import createPalette from '@material-ui/core/styles/createPalette';
import Loading from '../../common/component/Loading';

const Step03 = (props) => {
    const dispatch = useDispatch();

    const [files, setFiles] = useState([]);
    const [textFiles, setTextFiles] = useState([]);
    const [audioFiles, setAudioFiles] = useState('');
    const [text, setText] = useState('텍스트 열기');
    const [loading, setLoading] = useState(false);
    const [book, setBook] = useState('');

    const handleFileOnChange = (e) => {
        e.preventDefault();
        setFiles(e.target.files)
        var imagefiles = [];
        var fd = new FormData();
        for(var i = 0; i < e.target.files.length; i++){
            if(e.target.files[i].type =='application/pdf') fd.append('pdfFile', e.target.files[i]);
            if(e.target.files[i].type == 'image/png') fd.append('imageFiles', e.target.files[i]);
            if(e.target.files[i].type == 'image/jpeg') fd.append('imageFiles', e.target.files[i]);
        }
        setLoading(true);
        dispatch(makeTextFile(fd, props.book.id))
        .then((res) =>{
            setLoading(false);
            setBook(res.payload.data);
            alert('생성하기 버튼을 눌러주세요')
          })
          .catch((err) => {
              console.log(err)
              alert('오류가 발생했습니다')
              setLoading(false);
          });
    }
    const history = useHistory();

    const createAudioBook = (e) => {
        setLoading(true);
        dispatch(makeAudioFile(props.book.id))
        .then((res) =>{
            setLoading(false);
            alert('오디오북이 생성되었습니다')
            history.push({
                pathname: "/book",
                state: {book: book}
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
            <div className="loading-bar">
            {loading && <Loading title={'파일 변환 중...'}/>}
            </div>
            {!loading && 
            <>
            <h2 className="title">목소리로 변환해주세요</h2>
            <Grid container style={{marginTop: "13%"}}>
            <Grid item xs = {4}></Grid>
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
            <Grid item xs = {4}></Grid>
            </Grid>
            <button className="createBtn" onClick={createAudioBook}>생성하기</button>
            </>
            }
        </div>

    ); 
}
export default Step03;