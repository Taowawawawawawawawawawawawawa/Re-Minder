import React, { useEffect, useState } from "react";
import axios from "axios";

const QuestList = () => {
  const [quests, setQuests] = useState([]);

  // ฟังก์ชันดึงข้อมูลเควสทั้งหมดจาก backend
  const fetchQuests = async () => {
    try {
      const response = await axios.get("http://localhost:8202/quests/all");
      setQuests(response.data);  // เก็บข้อมูลเควสใน state
    } catch (error) {
      console.error("Error fetching quests:", error);
    }
  };

  // เมื่อ component ถูกโหลดขึ้นมาครั้งแรก จะดึงข้อมูลเควสทั้งหมด
  useEffect(() => {
    fetchQuests();
  }, []);  // array ว่างจะทำให้ useEffect รันแค่ครั้งเดียว

  return (
    <div>
      <h2>All Quests</h2>
      {quests.length > 0 ? (
        <ul>
          {quests.map((quest) => (
            <li key={quest.questId}>
              <h3>{quest.questName}</h3>
              <p>{quest.questDescription}</p>
              <p>Difficulty: {quest.difficulty}</p>
              <p>Beryl Reward: {quest.berylReward}</p>
              <p>Point Reward: {quest.pointReward}</p>
              <p>Quest Submit Method: {quest.questSubmitMethod}</p>
              <p>Available Time: {quest.availableTime.join(", ")}</p>
            </li>
          ))}
        </ul>
      ) : (
        <p>No quests available</p>
      )}
    </div>
  );
};

export default QuestList;
