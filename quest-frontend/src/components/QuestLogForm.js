import React, { useState, useEffect } from "react";
import { axiosInstance8203 } from "../api/quests"; // นำเข้า axiosInstance8203 สำหรับ quest log

const QuestLogList = () => {
  const [questLogs, setQuestLogs] = useState([]);

  const fetchQuestLogs = async () => {
    try {
      const response = await axiosInstance8203.get("/questlog/all"); // ใช้ axiosInstance8203 สำหรับ service quest log
      setQuestLogs(response.data);
    } catch (error) {
      console.error("Error fetching quest logs:", error);
    }
  };

  useEffect(() => {
    fetchQuestLogs();
  }, []);

  return (
    <div>
      <h2>All Quest Logs</h2>
      {questLogs.length > 0 ? (
        <ul>
          {questLogs.map((questLog) => (
            <li key={questLog.id}>
              {/* แสดงข้อมูลของ quest log */}
            </li>
          ))}
        </ul>
      ) : (
        <p>No quest logs available</p>
      )}
    </div>
  );
};

export default QuestLogList;