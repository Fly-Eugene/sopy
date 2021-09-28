import React, { useState } from 'react';
import { Link } from 'react-router-dom'
import { useDispatch } from 'react-redux';
import { findBook, findGenre } from "../../store/actions/bookActions";
import { useHistory } from 'react-router';
import Book from '../../common/component/Book'
import underImgSrc from '../../img/book_under.png';
import bookCover from '../../img/book-cover.jpg'
import nextBtn from '../../img/nextBtn.png'
import prevBtn from '../../img/prevBtn.png'
import searchImg from '../../img/search.png'
import './FindBookSearch.modules.scss';
import { Grid } from '@material-ui/core';

const FindBookSearch = (props) => {
    const [search, setSearch] = useState('')
    const [genre, setGenre] = useState(['호러', '판타지', '소설', '과학', '역사', '로맨스', '철학', '수학', '컴퓨터'])
    const [bookList, setBookList] = useState([])
    
    const onSearchHandler = (e) => { setSearch(e.target.value)}
    const onGenreHandler = (params, e) => {
        console.log(params);
        const list = document.getElementsByTagName('li');
        for(var i = 0; i < list.length; i++){
            list[i].style.color = 'gray';
        }
        e.target.style.color = 'black';
        setSearch('');
        dispatch(findGenre(params)).payload
        .then((res) =>{
            console.log(res.data.books);
            setBookList([]);
            for(var i = 0; i < res.data.books.length; i++){
                console.log(res.data.books[i])
                setBookList(bookList => [...bookList, res.data.books[i]])
            }
            console.log(bookList)
          })
          .catch((err) => {
              console.log(err)
          });
    }
    const onKeyPress = (e) => {
        if(e.key == 'Enter'){
            FindBook();
        }
    }
    const dispatch = useDispatch();
    const history = useHistory();
    const prev = () => {
        return 
      }
      const next = () => {
        return
      }
    const FindBook = (e) => {
        // e.preventDefault();
        JSON.stringify('')
        const body = {
            title: search
        }
        dispatch(findBook(body)).payload
        .then((res) =>{
            console.log(res.data.books);
            setBookList([]);
            for(var i = 0; i < res.data.books.length; i++){
                console.log(res.data.books[i])
                setBookList(bookList => [...bookList, res.data.books[i]])
            }
            console.log(bookList)
          })
          .catch((err) => {
              console.log(err)
          });
    }
    const moveDetail = (params, e) =>{
        history.push({
            pathname: "/book",
            state: {book: params}
        });
    }
    return (
        <div className="findbook">
            <h1>듣고싶은 책을 찾아보세요</h1>
            <div className="searchBox">
            <input value={search} onChange={onSearchHandler} onKeyPress={onKeyPress}/>
            <img src={searchImg} onClick={FindBook}/>
            </div>
            <ul>
                {
                    genre.map((g, index) => <li key={index} onClick={(e) => {onGenreHandler(g, e)}}>{g}</li>)
                }
            </ul>
            <div className="search-book-container">
                <div className="search-book-inner">
                    <Grid container className="book-container">
                        {
                            bookList.map((book) => 
                                <Grid item xs={4} onClick={(e) => {moveDetail(book, e)}}>
                                <Book underImgSrc={underImgSrc} bookCover={book.bookImage.path + book.bookImage.imageName}/>
                                <p>{book.title}</p>
                                </Grid>
                            )
                        }
                    </Grid>
                </div>
                <div className="botton-direction">
                    <div className="prevBtn" onClick={prev}>
                    <img src={prevBtn} alt="prevBtn"/>
                    </div>
                    <div className="nextBtn" onClick={next}>
                    <img src={nextBtn} alt="nextBtn"/>
                    </div>
                </div>
            </div>
        </div>
    ); 
    
}
export default FindBookSearch;
