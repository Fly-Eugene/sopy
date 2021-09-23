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