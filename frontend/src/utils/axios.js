import axios from "axios";

const DOMAIN = "http://localhost:5000";

export const request = (method, url, data, headers) => {
    return axios({
        method,
        url: DOMAIN + url,
        data,
        headers
    })
}