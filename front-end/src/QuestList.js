import React, { useEffect, useState } from 'react';
import axios from 'axios';

const QuestList = () => {
  const [quests, setQuests] = useState([]);

  useEffect(() => {
    // ดึงข้อมูลจาก microservice-quests
    axios.get('http://localhost:8202/quests/all')
      .then(response => {
        setQuests(response.data);
      })
      .catch(error => {
        console.error('Error fetching quests:', error);
      });
  }, []);

  // ฟังก์ชันเมื่อกด "Start Quest"
  const startQuest = (questId) => {
    // ส่งคำขอไปยัง backend เพื่อเริ่มต้นเควส
    axios.post(`http://localhost:8202/quests/start/${questId}`)
      .then(response => {
        alert('Quest started!');
        // อัปเดตสถานะของ quest ใน UI
        setQuests(prevQuests =>
          prevQuests.map(quest =>
            quest.questId === questId ? { ...quest, status: 'In Progress' } : quest
          )
        );
      })
      .catch(error => {
        console.error('Error starting quest:', error);
        alert('Failed to start quest!');
      });
  };

  return (
    <div>
      <h1>Quest List</h1>
      <ul>
        {quests.map(quest => (
          <li key={quest.questId}>
            <h2>{quest.questName}</h2>
            <p>{quest.questDescription}</p>
            <p>Reward: {quest.berylReward} Beryl, {quest.pointReward} Points</p>
            {/* เพิ่มฟังก์ชัน startQuest เมื่อกดปุ่ม */}
            <button onClick={() => startQuest(quest.questId)}>Start Quest</button>
            {/* แสดงสถานะของเควส */}
            <p>Status: {quest.status}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default QuestList;