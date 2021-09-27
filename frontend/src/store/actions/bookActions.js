import axios from "axios";
import { request } from "../../utils/axios";

const BOOK_URL = "/book";

export function makeBook(item) {
    const headers = {
        'Content-type' : 'multipart/form-data'
    }
    console.log(item)
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
    const data = axios.get("http://localhost:5000/book/genre?genre=" + item)
    return{
        type: "GENRE_BOOK",
        payload: data
    }
}