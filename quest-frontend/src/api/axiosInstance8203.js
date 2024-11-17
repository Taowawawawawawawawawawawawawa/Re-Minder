import axios from 'axios';

const axiosInstance8203 = axios.create({
    baseURL: 'http://localhost:8203', // URL ของ backend ที่รันบน port 8203
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true
});

export { axiosInstance8203 };