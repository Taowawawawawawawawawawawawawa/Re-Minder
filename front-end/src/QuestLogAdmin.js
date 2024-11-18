import React, { useEffect, useState } from 'react';
import axios from 'axios';

const QuestLogAdmin = () => {
  const [questLogs, setQuestLogs] = useState([]);

  useEffect(() => {
    // ดึงข้อมูลจาก microservice-questlog
    axios.get('http://localhost:8203/questlogs/all')  // URL ของ microservice-questlog
      .then(response => {
        console.log('Fetched quest logs:', response.data);  // ตรวจสอบข้อมูลที่ได้รับ
        setQuestLogs(response.data);  // ตั้งค่า state
      })
      .catch(error => {
        console.error('Error fetching quest logs:', error);
      });
  }, []);

  const handleStatusChange = (questLogId, newStatus) => {
    axios.put(`http://localhost:8203/questlogs/${questLogId}/status`, { status: newStatus })
      .then(response => {
        alert(`Quest Log Status Updated to ${newStatus}`);
        setQuestLogs(questLogs.map(log => 
          log.id === questLogId ? { ...log, status: newStatus } : log
        ));
      })
      .catch(error => {
        console.error('Error updating status:', error);
      });
  };

  return (
    <div>
      <h1>Admin Quest Logs</h1>
      {questLogs.length > 0 ? (
        <ul>
          {questLogs.map(log => (
            log.id ? (
              <li key={log.id}>
                <h2>{log.questName}</h2>
                <p>Submitted by User: {log.userId}</p>
                <p>Status: {log.status}</p>
                <img src={log.imageUrl} alt="Quest submission" width="200" />
                <div>
                  <button onClick={() => handleStatusChange(log.id, 'Approved')}>Approve</button>
                  <button onClick={() => handleStatusChange(log.id, 'Rejected')}>Reject</button>
                </div>
              </li>
            ) : null
          ))}
        </ul>
      ) : (
        <p>No quest logs available.</p>  // ถ้าไม่มีข้อมูลจะแสดงข้อความนี้
      )}
    </div>
  );
};

export default QuestLogAdmin;