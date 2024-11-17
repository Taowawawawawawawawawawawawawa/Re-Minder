import axios from 'axios';

const axiosInstance8202 = axios.create({
    baseURL: 'http://localhost:8202', // URL ของ backend ที่รันบน port 8202
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true // เปิดการส่งข้อมูลการรับรอง เช่น cookies หรือ token
});

export { axiosInstance8202 };