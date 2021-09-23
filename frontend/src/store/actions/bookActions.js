import { request } from "../../utils/axios";

const BOOK_URL = "/book";

export function makeBook(item) {
    const data = request("post", BOOK_URL, item);

    return{
        type: "ADD_BOOK",
        payload: data
    }
}