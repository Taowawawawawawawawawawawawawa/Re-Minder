import React, { useState, useEffect } from 'react';
import QuestForm from './components/QuestForm';
import QuestList from './components/QuestList';
import { axiosInstance8202, axiosInstance8203 } from './api/quests';  // นำเข้าจาก api/quests.js


const App = () => {
  const [quests, setQuests] = useState([]);
  const [selectedQuest, setSelectedQuest] = useState(null);

  // ฟังก์ชันสำหรับดึงข้อมูลเควสทั้งหมด
  const fetchQuests = async () => {
    try {
      const response = await axiosInstance8202.get('/all'); // ใช้ axiosInstance8202 สำหรับดึงข้อมูล
      setQuests(response.data);
    } catch (error) {
      console.error('Failed to fetch quests:', error);
    }
  };

  // ฟังก์ชันสำหรับสร้างเควสใหม่
  const handleCreateQuest = async (newQuest) => {
    try {
      const response = await axiosInstance8202.post('/create', newQuest); // ใช้ axiosInstance8202 สำหรับสร้างเควส
      if (response.status === 201) {
        alert('Quest created successfully!');
        fetchQuests();
      } else {
        alert('Failed to create quest.');
      }
    } catch (error) {
      console.error('Error creating quest:', error);
      alert('Failed to create quest.');
    }
  };

  // ฟังก์ชันสำหรับเลือกเควส
  const handleSelectQuest = (quest) => {
    setSelectedQuest(quest);
    console.log("Selected Quest:", quest);
  };

  useEffect(() => {
    fetchQuests();
  }, []);

  return (
    <div>
      <h1>Quest Management</h1>
      <QuestForm onCreate={handleCreateQuest} />
      <QuestList quests={quests} onSelectQuest={handleSelectQuest} />
      {selectedQuest && (
        <div>
          <h3>Selected Quest Details</h3>
          <p>{selectedQuest.questName} - {selectedQuest.difficulty}</p>
          <button onClick={() => submitQuest(selectedQuest)}>Submit Quest</button>
        </div>
      )}
    </div>
  );
};

const submitQuest = async (quest) => {
  try {
    const response = await axiosInstance8203.post('/questlog/submit', {  // ใช้ axiosInstance8203 สำหรับส่งข้อมูลไปยัง questlog
      questId: quest.questId,
      questName: quest.questName,
      questDescription: quest.questDescription,
      difficulty: quest.difficulty,
      berylReward: quest.berylReward,
      pointReward: quest.pointReward,
      status: "Pending Review",
    });

    if (response.status === 200) {
      alert("Quest submitted successfully!");
    } else {
      alert("Failed to submit quest.");
    }
  } catch (error) {
    console.error("Error submitting quest:", error);
    alert("Failed to submit quest.");
  }
};


export default App;