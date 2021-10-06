import { request } from "../../utils/axios";

const USER_URL = "/user";

export function join(item) {
    const headers = {
        'Content-type' : 'multipart/form-data'
    }
    const data = request("post", USER_URL + "/join", item, {headers});

    return{
        type: "ADD_USER",
        payload: data
    }
}

export function login(item) {
    const data = request("post", USER_URL + "/login", item);

    return {
        type: "LOGIN_USER",
        payload: data
    }
}

