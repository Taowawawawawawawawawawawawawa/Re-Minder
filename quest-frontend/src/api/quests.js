// src/api/quests.js
import axios from 'axios';

// สำหรับ backend ที่รันบน port 8202
const axiosInstance8202 = axios.create({
    baseURL: 'http://localhost:8202', // URL ของ backend ที่รันบน port 8202
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true // เปิดการส่งข้อมูลรับรอง
});

// สำหรับ backend ที่รันบน port 8203
const axiosInstance8203 = axios.create({
    baseURL: 'http://localhost:8203', // URL ของ backend ที่รันบน port 8203
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true // เปิดการส่งข้อมูลรับรอง
});

// Export axios instances สำหรับการใช้ใน component ต่างๆ
export { axiosInstance8202, axiosInstance8203 };