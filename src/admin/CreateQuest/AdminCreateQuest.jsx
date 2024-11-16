import React, { useState } from 'react';
import './AdminCreateQuest.css';
import { Link } from 'react-router-dom'; // Import Link
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const AdminCreateQuest = () => {
  const [questName, setQuestName] = useState('');
  const [questDetails, setQuestDetails] = useState('');
  const [questMethod, setQuestMethod] = useState('');
  const [difficultyLevel, setDifficultyLevel] = useState('');

  const handleCreateQuest = () => {
    // Logic to create the quest
    alert(`Quest "${questName}" created successfully!`);
  };

  return <><Navbar />
    <div className="admin-create-quest">
      <div className="quest-detail">
        <h2>Quest Detail</h2>
        <input
          type="text"
          placeholder="ชื่อเควส"
          value={questName}
          onChange={(e) => setQuestName(e.target.value)}
        />
        <select
          value={questDetails}
          onChange={(e) => setQuestDetails(e.target.value)}
        >
          <option value="">ช่องใส่รายละเอียดเควส</option>
          <option value="detail1">Detail 1</option>
          <option value="detail2">Detail 2</option>
        </select>
        <input
          type="text"
          placeholder="วิธีส่งงาน"
          value={questMethod}
          onChange={(e) => setQuestMethod(e.target.value)}
        />
        <select
          value={difficultyLevel}
          onChange={(e) => setDifficultyLevel(e.target.value)}
        >
          <option value="">ระดับความยาก (ที่คาดใช้)</option>
          <option value="easy">ง่าย</option>
          <option value="medium">กลาง</option>
          <option value="hard">ยาก</option>
        </select>
        <button onClick={handleCreateQuest}>สร้างเควส</button>
        {/* <button onClick={handleCreateQuest}>สร้างเควส</button> */}
      </div>

      <div className="quest-list">
        <h2>Quest List</h2>
        <input type="text" placeholder="ชื่อเควส/รายละเอียด" />
        <button className="search-btn">🔍</button>
        <div className="quest-categories">
          <button>ง่าย</button>
          <button>กลาง</button>
          <button>ยาก</button>
        </div>
        <div className="quest-items">
          <div className="quest-item">ลองนอนเฉยๆ <span>สร้างแล้ว</span></div>
          <div className="quest-item">ขอกำลังใจหน่อย <span>สร้างแล้ว</span></div>
        </div>
        <div className="section quest-section"></div>
        <Link to="/AdminQuestBoard" className="quest-link">
          <p>ดูเควสบอร์ด</p>
        </Link>
        
      </div>
    </div>

    <Footer /></>
};

export default AdminCreateQuest;
