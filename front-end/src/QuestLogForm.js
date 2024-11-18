import React, { useState } from 'react';
import axios from 'axios';

// cd microservice-questlog

const QuestLogForm = ({ questId, userId }) => {
  const [imageUrl, setImageUrl] = useState('');
  const [status, setStatus] = useState('Pending');

  const handleSubmit = (e) => {
    e.preventDefault();

    const questLogData = {
      questId,
      userId,
      imageUrl,
      status,
    };

    // ส่งข้อมูลไปยัง microservice-questlog
    axios.post('http://localhost:8203/questlogs/create', questLogData)  // แก้ไข URL เป็น "create" แทน "creat"
      .then(response => {
        alert('Quest Submitted successfully!');
      })
      .catch(error => {
        console.error('Error submitting quest:', error);
      });
  };

  return (
    <div>
      <h1>Submit Your Quest</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Quest Image URL:</label>
          <input
            type="text"
            value={imageUrl}
            onChange={(e) => setImageUrl(e.target.value)}
            placeholder="Enter image URL"
            required
          />
        </div>
        <div>
          <label>Status:</label>
          <select value={status} onChange={(e) => setStatus(e.target.value)}>
            <option value="Pending">Pending</option>
            <option value="Approved">Approved</option>
            <option value="Rejected">Rejected</option>
          </select>
        </div>
        <button type="submit">Submit Quest</button>
      </form>
    </div>
  );
};

export default QuestLogForm;