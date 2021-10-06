import React , {useState, useEffect} from 'react';
import './BookDetailTitle.modules.scss'
import bookCover from '../../img/book-cover.jpg'
import { FaHeart, FaRegHeart } from 'react-icons/fa'
import { useDispatch } from 'react-redux';
import { getLikes, addLike, deleteLike} from '../../store/actions/bookActions'

export default function BookDetailTitle(props) {
  const [isLike, setLike] = useState(false);
  const [voice, setVoice] = useState('');

  const book = props.book;
  const dispatch = useDispatch();
  useEffect(() => {
    getLikesHandler();
  })

  const getLikesHandler = (e) => {
    dispatch(getLikes())
    .then((res) => {
      if(res.payload.data.book.length != 0 && res.payload.data.book.filter(function (it) { return it.id === book.id}).length > 0){
        setLike(true)
      }
    })
    .catch((err) => {
      console.log(err)
      alert('오류가 발생했습니다')
    })
    
  }

  const addLikeHandler = (e) => {
    const data = {
      "bookId" : book.id
    }
    dispatch(addLike(data))
    .then((res) => {
      setLike(true)
    })
    .catch((err) => {
      console.log(err)
      alert('오류가 발생했습니다')
    })
  }

  const deleteLikeHandler = (e) => {
    const data = {
      "bookId" : book.id
    }
    dispatch(deleteLike(data))
    .then((res) => {
      setLike(false)
    })
    .catch((err) => {
      console.log(err)
      alert('오류가 발생했습니다')
    })
  }
  const VoiceInput = (e) => {
    let voiceIndex = e.target.options.selectedIndex
    setVoice(`${e.target.options[voiceIndex].value}`)
  }

  const sendVoice = () => {props.getVoice(voice)}
  return (
    <div className="title-container">
      <div className="title-inner">
        <div className="title-left">
          <img src={book.bookImage.path + book.bookImage.imageName} alt="bookCover"/>

        </div>
        <div className="title-right">
            {!isLike && <FaRegHeart onClick={addLikeHandler} style={{cursor: 'pointer'}}/>}
            {isLike && <FaHeart onClick={deleteLikeHandler} style={{cursor: 'pointer'}}/>}
          <div className="book-title">
            <p className="book-name">{book.title}</p>
            <p className="book-writer">{book.author}</p>
            <p className="book-translator">{book.translator} 옮김</p>
            <div className="book-info">
              <div>
                <p>장르</p>
                <p>{book.genre}</p>
              </div>
              <div>
                <p>출판사</p>
                <p>{book.publisher}</p>
              </div>
              <div>
                <p>발간일</p>
                <p>{book.publishedDate}</p>
              </div>
            </div>
          </div>
          <select className="voice" name="voice" onChange={VoiceInput} onBlur={sendVoice}>
            <option value=''>성우 선택</option>
            <option value='1'>성우1</option>
            <option value='2'>성우2</option>
          </select>
        </div>
      </div>
    </div>
  )
}