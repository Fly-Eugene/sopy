import axios from "axios";
import { request } from "../../utils/axios";

const BOOK_URL = "/book";

export function makeBook(item) {
    const headers = {
        'Content-type' : 'multipart/form-data',
        Authorization : `Bearer ${localStorage.getItem('jwt')}`
    }
    const data = request("post", BOOK_URL, item, {headers});

    return{
        type: "ADD_BOOK",
        payload: data
    }
}

export function findBook(item) {
    const data = request("post", BOOK_URL + "/search", item);
    return {
        type: "FIND_BOOK",
        payload: data
    }
}

export function findGenre(item){
    const data = request("get", BOOK_URL + "/genre?genre=" + item);
    return{
        type: "GENRE_BOOK",
        payload: data
    }
}

export function makeTextFile(item, id){
    const headers = {
        'Content-type' : 'multipart/form-data',
    }
    const data = request("post", BOOK_URL + "/text/" + id, item, {headers})
    return{
        type: "MAKE_TEXT",
        payload: data
    }
}

export function makeAudioFile(item){
    const headers = {
        Authorization : `Bearer ${localStorage.getItem('jwt')}`
    }
    const data = request("post", BOOK_URL + "/audio/" + item, headers)
    return{
        type: "MAKE_AUDIO",
        payload: data
    }
}

export function getTextFile(id, page){
    const headers = {
        Authorization : `Bearer ${localStorage.getItem('jwt')}`
    }
    const data = request('get', BOOK_URL + "/text/" + id + "?bookPage=" + page, '', headers)
    return{
        type: "GET_TEXT",
        payload: data
    }
}

export function getAudioFile(id, page){
    const headers = {
        Authorization : `Bearer ${localStorage.getItem('jwt')}`
    }
    const data = request('get', BOOK_URL + "/audio/" + id + "?bookPage=" + page,'', headers)
    return{
        type: "GET_AUDIO",
        payload: data
    }
}

export function addLike(item){
    const headers = {
        Authorization : `Bearer ${localStorage.getItem('jwt')}`
    }
    const data = request("post", BOOK_URL + "/like", item, headers)
    return{
        type:"ADD_LIKE",
        payload: data
    }
}

export function deleteLike(item){
    const headers = {
        Authorization : `Bearer ${localStorage.getItem('jwt')}`
    }
    const data = request("delete", BOOK_URL + "/like", item, headers)
    return{
        type:"DELETE_LIKE",
        payload: data
    }
}

export function getLikes(){
    const headers = {
        Authorization : `Bearer ${localStorage.getItem('jwt')}`
    }
    const data = request("get", BOOK_URL + "/like", '', headers)
    return{
        type:"GET_LIKE",
        payload: data
    }
}

export function getRead(){
    const headers = {
        Authorization : `Bearer ${localStorage.getItem('jwt')}`
    }
    const data = request("get", BOOK_URL + "/readlist", '', headers)
    return{
        type:"GET_READ",
        payload: data
    }
}

export function addBookmark(item){
    const headers = {
        Authorization : `Bearer ${localStorage.getItem('jwt')}`
    }
    const data = request("post", BOOK_URL + "/bookmark", item, headers)
    return{
        type:"ADD_BOOKMARK",
        payload: data
    }
}

export function getBookmark(id){
    const headers = {
        Authorization : `Bearer ${localStorage.getItem('jwt')}`
    }
    const data = request("get", BOOK_URL + "/bookmark/" + id, '', headers)
    return{
        type:"GET_BOOKMARK",
        payload: data
    }    
}

export function deleteBookmark(id){
    const headers = {
        Authorization : `Bearer ${localStorage.getItem('jwt')}`
    }
    const data = request("delete", BOOK_URL + "/bookmark/" + id, '', headers)
    return{
        type:"DELETE_BOOKMARK",
        payload: data
    }  
}