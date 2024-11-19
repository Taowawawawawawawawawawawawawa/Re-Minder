import React, { useEffect, useState } from 'react';
import "./AdminQuestBoard.css";
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const AdminQuestBoard = () => {
  const [NameQuests, setNameQuests] = useState([]); // Quests pending approval
  const [selectedQuest, setSelectedQuest] = useState(null); // State to track selected quest
  const [error, setError] = useState(null);

  // Fetch pending quests
  const fetchNameQuests = async () => {
    try {
      const response = await fetch('http://localhost:8203/questlogs/pending');
      if (!response.ok) {
        throw new Error(`API error: ${response.status}`);
      }
      const questLogs = await response.json();

      // Fetch user data for each quest log
      const usersData = await Promise.all(
        questLogs.map(async (quest) => {
          const userResponse = await fetch(`http://localhost:8200/users/${quest.userId}`);
          if (!userResponse.ok) {
            throw new Error(`Failed to fetch user data for userId: ${quest.userId}`);
          }
          const user = await userResponse.json();
          return { ...quest, senderName: user.name }; // Combine quest log with user name
        })
      );

      setNameQuests(usersData);
    } catch (err) {
      setError(err.message);
    }
  };

  // Fetch data on component mount
  useEffect(() => {
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
                  <p>
                    สิ่งที่ส่ง:{" "}
                    {selectedQuest.imageUrl ? (
                      <img
                        src={selectedQuest.imageUrl}
                        alt="Submitted Content"
                        style={{ maxWidth: "100%", height: "auto" }}
                      />
                    ) : selectedQuest.submitText ? (
                      selectedQuest.submitText
                    ) : (
                      "ไม่มีข้อมูล"
                    )}
                  </p>
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
