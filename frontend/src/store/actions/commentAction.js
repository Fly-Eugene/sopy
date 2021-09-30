import axios from "axios";
import { request } from "../../utils/axios";

const COMMENT_URL = "/comment";
const headers = {
    Authorization : `Bearer ${localStorage.getItem('jwt')}`
}

export function getComment(id) {
    const data = request("get", COMMENT_URL + "?bookId=" + id);

    return{
        type: "GET_COMMENT",
        payload: data
    }
}

export function addComment(item) {
    const data = request("post", COMMENT_URL, item, headers);
   
    return{
        type: "ADD_COMMENT",
        payload: data
    }
}

export function deleteComment(id){
    const data = request("delete", COMMENT_URL + "/" + id, '', headers);

    return {
        type: "DELETE_COMMENT",
        payload: data
    }
}

export function modifyComment(item){
    const data = request("patch", COMMENT_URL, item, headers);

    return{
        type: "MODIFY_COMMENT",
        payload: data
    }
}