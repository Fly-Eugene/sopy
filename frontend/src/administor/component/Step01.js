import React, {Component} from 'react';
import coverImg from '../../img/cover.png'
import './Step01.modules.scss'

class Step01 extends Component {
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
        let cover = null;
        if(this.state.file !== ''){
            cover = <img className='cover' src={this.state.preview} alt='book-cover'></img>
        }else{
            cover = <img className='cover' src={coverImg} alt='book-cover'></img>
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
                        onChange={this.handleFileOnChange}/>
                </div>
            </div>
        ); 
    }
}
// const Step01 = () => {
//     return (
//         <div>
//             <h2>책 표지를 선택해주세요</h2>
            
//             <Btn>사진 선택</Btn>
//         </div>
//     );
// }
export default Step01;