import React, {Component} from 'react';
import {Grid} from '@material-ui/core';
class Step03 extends Component {
    constructor(props){
        super(props);
        this.state = {
            file: '',
            preview: ''
        }
    }
    handleFileOnChange = (e) => {
        e.preventDefault();
        let reader = new FileReader();
        let file = e.target.files[0];
        reader.onloadend = () =>{
            this.setState({
                file: file,
                preview: reader.result
            })
        }
        reader.readAsDataURL(file);
    }
    render(){
        let filename = 'PDF/JPEG 넣기';
        if(this.state.file !== ''){
            filename = this.state.file.name;
        }
        return (
            <div className="step03">
                <h2>목소리로 변환해주세요</h2>
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
                                onChange={this.handleFileOnChange}/>
                            {/* <div class="fileName"></div> */}
                            </div>
                        </Grid>
                        <Grid item xs = {4}>
                            <div class="fileCard"><label for="text_file">텍스트 넣기</label>
                            <input type="file"
                                id="text_file" 
                                name="file"
                                style={{display:"none"}}
                                onChange={this.handleFileOnChange}/>
                            </div>
                        </Grid>
                        <Grid item xs = {4}>
                            <div class="fileCard"><label for="voice_file">성우 선택</label>
                            <input type="file"
                                id="voice_file" 
                                name="file"
                                style={{display:"none"}}
                                onChange={this.handleFileOnChange}/>
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
}
export default Step03;