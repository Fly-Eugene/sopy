import axios from "axios";

const DOMAIN = "http://localhost:5000";

export const request = (method, url, data, headers) => {
    return axios({
        method,
        url: DOMAIN + url,
        data,
        headers
    })
    .then((res) => console.log(res))
    .catch((err) => {
        console.log(err)
        alert('오류가 발생했습니다')
    });
}