import React, { useEffect, useState, useCallback } from 'react';
import './BookDetailComment.modules.scss'
import image from '../../img/account_circle.png'
import pencil from '../../img/create.png'
import { useDispatch } from 'react-redux';
import { getComment, addComment, deleteComment, modifyComment } from '../../store/actions/commentAction';
import { useSelector } from 'react-redux';

export default function BookDetailComment(props) {
  const user = useSelector(state => state.userReducer[0].data.user);
  const [commentList, setCommentList] = useState([]);
  const [comment, setComment] = useState('');
  const [modifyCommentitem, setModify] = useState('');
  const [mode, setMode] = useState('');
  const [modeId, setModeId] = useState('');

  const dispatch = useDispatch();
  
  useEffect(() => {
    getCommentList();
  }, [])
  
  const getCommentList = (e) => {
    dispatch(getComment(props.id))
    .then(res => {
      setCommentList(res.payload.data);
    })
    .catch((err) => {
      console.log(err)
      alert('오류가 발생했습니다')
    });
  }
  const onKeyPress = (e) => {
    if(e.key == 'Enter'){
        addCommentHandler();
    }
}
  const CommentInput = useCallback((e) => setComment(e.target.value), []);
  const ModifyCommentInput = useCallback((e) => setModify(e.target.value), []);

  const addCommentHandler = e => {
    const data = {
      bookId: props.id,
      content: comment,
      userId: user.id
    }
    dispatch(addComment(data))
    .then((res) =>{
      alert('댓글이 등록되었습니다')
      getCommentList();
      setComment('');
    })
    .catch((err) => {
        console.log(err)
        alert('오류가 발생했습니다')
    });
  }

  const deleteCommentHandler = (params, e) => {
    dispatch(deleteComment(params))
    .then((res) => {
      alert('댓글이 삭제되었습니다.')
      getCommentList();
    })
    .catch((err) => {
      alert('오류가 발생했습니다')
    })
  }

  const modifyCommentHandler = (params, e) => {
    const data = {
      id: params,
      bookId: props.id,
      content: modifyCommentitem,
      userId: user.id
    }
    dispatch(modifyComment(data))
    .then((res) =>{
      alert('댓글이 수정되었습니다')
      getCommentList();
      setMode('')
      setModeId(0)
    })
    .catch((err) => {
        console.log(err)
        alert('오류가 발생했습니다')
    });
  }

  const changeModifyMode = (id, e) => {
    setMode('수정');
    setModeId(id);
  }
  return (
    <div className="comment-container">
      <h2>댓글</h2>
      <div className="conmment">
        <div className="comment-content">
        {
          commentList.map((comment, index) => 
          <div key={index} className = "comment-content-item">
            <img src={image}/>
            { modeId != comment.id && <div className="comment">{comment.content}</div>}
            {
              mode == '수정' && modeId == comment.id &&
              user.id == comment.userId && <input className="comment" value={modifyCommentitem} onChange={ModifyCommentInput}/>
            }
            { user.id == comment.userId && modeId != comment.id &&
            <p>
              <span style={{color:'blue'}} onClick={e => changeModifyMode(comment.id, e)}>수정</span> | <span style={{color:'red'}} onClick={e => deleteCommentHandler(comment.id, e)}>삭제</span>
            </p>}
            { user.id == comment.userId && mode == '수정' && modeId == comment.id &&
              <p>
              <span onClick={e => modifyCommentHandler(comment.id, e)}>확인</span>
            </p>}
          </div>
          )
        }
      </div>
        <div className = "add-comment">
          <input className="input-comment" value={comment} onChange={CommentInput} onKeyPress={onKeyPress}/>
          <div>
            <img src={pencil} onClick={addCommentHandler}></img>
          </div>
        </div>
      </div>
    </div>
  )
}