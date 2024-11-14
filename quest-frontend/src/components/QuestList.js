import React, { useState, useEffect } from 'react';
import { axiosInstance8202, axiosInstance8203 } from '../api/quests';

const QuestList = () => {
    const [quests, setQuests] = useState([]);

    useEffect(() => {
        // เรียก API จาก backend ที่รันบน port 8202
        axiosInstance8202.get('/quests/all')
            .then((response) => {
                setQuests(response.data);
            })
            .catch((error) => {
                console.error("Error fetching quests from 8202", error);
            });

        // ถ้าต้องการเรียกจาก backend อีกตัวที่รันบน port 8203
        axiosInstance8203.get('/quests/all')
            .then((response) => {
                setQuests(response.data);
            })
            .catch((error) => {
                console.error("Error fetching quests from 8203", error);
            });
    }, []);

    return (
        <div>
            <h1>Quest List</h1>
            <ul>
                {quests.map((quest) => (
                    <li key={quest.questId}>{quest.questName}</li>
                ))}
            </ul>
        </div>
    );
};

export default QuestList;