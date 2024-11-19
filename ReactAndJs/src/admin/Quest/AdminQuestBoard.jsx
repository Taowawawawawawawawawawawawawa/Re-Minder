import React, { useEffect, useState } from 'react';
import "./AdminQuestBoard.css";
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const AdminQuestBoard = () => {

  const [NameQuests, setNameQuests] = useState([]); // Quests pending approval
  const [DetailQuest, setDetailQuest] = useState([]); // All quest details
  const [selectedQuest, setSelectedQuest] = useState(null); // State to track selected quest
  const [error, setError] = useState(null);

  // Fetch pending quests
  const fetchNameQuests = async () => {
    try {
      const response = await fetch('http://localhost:8203/questlogs/pending');
      if (!response.ok) {
        throw new Error(`API error: ${response.status}`);
      }
      const data = await response.json();
      setNameQuests(data);
    } catch (err) {
      setError(err.message);
    }
  };

  // Fetch all quest details
  const fetchDetailQuest = async () => {
    try {
      const response = await fetch('http://localhost:8202/quests/all');
      if (!response.ok) {
        throw new Error(`API error: ${response.status}`);
      }
      const data = await response.json();
      setDetailQuest(data);
    } catch (err) {
      setError(err.message);
    }
  };

  // Fetch data on component mount
  useEffect(() => {
    fetchDetailQuest();
    fetchNameQuests();
  }, []);

  // Handle quest selection
  const handleQuestClick = (quest) => {
    setSelectedQuest(quest); // Set the selected quest
  };

  return (
    <>
      <Navbar />
      <div className="admin-quest-board">
        <div className="quest-container">
          
          {/* Quest List */}
          <div className="quest-list">
            <h2>Quest List</h2>
            <ul className="quest-items">
              {NameQuests.map((detailquest) => (
                <li
                  key={detailquest.questId} // Use unique questId as key
                  className="quest-item"
                  onClick={() => handleQuestClick(detailquest)} // Handle quest click
                >
                  <p>{detailquest.questName}</p>
                  <span className="quest-status">{detailquest.status}</span>
                </li>
              ))}
            </ul>
          </div>
          
          {/* Quest Detail */}
          <div className="quest-detail">
            <h2>Detail</h2>
            {selectedQuest ? (
              <>
              
                <p className="quest-title">{selectedQuest.questName}</p>
                <div className="quest-info">
                  <p>ชื่อผู้ส่ง: {selectedQuest.senderName || "ไม่มีข้อมูล"}</p>
                  <p>สิ่งที่ส่ง: {selectedQuest.deliveryMethod || "ไม่มีข้อมูล"}</p>
                  <p>ระดับความยาก: {selectedQuest.difficulty || "ไม่มีข้อมูล"}</p>
                </div>
              </>
            ) : (
              <p>กรุณาเลือกเควสจากรายการด้านซ้าย</p>
            )}
            <div className="action-buttons">
              <button className="cancel-quest">ไม่อนุมัติ</button>
              <button className="approve-quest">อนุมัติ</button>
            </div>
            <input type="text" className="reason-input" placeholder="เหตุผล" />
          </div>
          
        </div>
      </div>
      <Footer />
    </>
  );
};

export default AdminQuestBoard;
