import React, { useEffect, useState } from 'react';
import "./AdminQuestBoard.css";
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const AdminQuestBoard = () => {
  const [NameQuests, setNameQuests] = useState([]); // Quests pending approval
  const [selectedQuest, setSelectedQuest] = useState(null); // State to track selected quest
  const [error, setError] = useState(null);
  const [reason, setReason] = useState(""); // Reason for rejecting quest

  // Fetch pending quests
  const fetchNameQuests = async () => {
    try {
        const response = await fetch("http://localhost:8203/questlogs/pending");
        if (!response.ok) {
            throw new Error(`API error: ${response.status}`);
        }
        const questLogs = await response.json();

        // Fetch user data for each quest log
        const enrichedQuests = await Promise.all(
            questLogs.map(async (quest) => {
                const userResponse = await fetch(`http://localhost:8200/users/${quest.userId}`);
                if (!userResponse.ok) {
                    throw new Error(`Failed to fetch user data for userId: ${quest.userId}`);
                }
                const user = await userResponse.json();
                return { ...quest, senderName: user.name }; // Combine quest log with user name
            })
        );

        setNameQuests(enrichedQuests);
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

  // Handle approve and reject actions
  const handleApprove = async () => {
    if (selectedQuest) {
      try {
        const response = await fetch(`http://localhost:8203/questlogs/${selectedQuest.questId}/approve`, {
          method: 'PATCH',
        });
        if (response.ok) {
          alert("เควสต์ได้รับการอนุมัติแล้ว");
          fetchNameQuests(); // Re-fetch pending quests
          setSelectedQuest(null);
        } else {
          alert("เกิดข้อผิดพลาดในการอนุมัติเควสต์");
        }
      } catch (error) {
        alert("เกิดข้อผิดพลาดในการอนุมัติเควสต์");
      }
    }
  };

  const handleReject = async () => {
    if (selectedQuest && reason.trim()) {
      try {
        const response = await fetch(`http://localhost:8203/questlogs/${selectedQuest.questId}/reject`, {
          method: 'PATCH',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ reason }),
        });

        if (response.ok) {
          alert("เควสต์ถูกปฏิเสธแล้ว");
          fetchNameQuests(); // Re-fetch pending quests
          setSelectedQuest(null);
        } else {
          alert("เกิดข้อผิดพลาดในการปฏิเสธเควสต์");
        }
      } catch (error) {
        alert("เกิดข้อผิดพลาดในการปฏิเสธเควสต์");
      }
    } else {
      alert("กรุณากรอกเหตุผลในการปฏิเสธ");
    }
  };

  return (
    <>
      <Navbar />
      <div className="admin-quest-board">
        <div className="quest-container">
          
          {/* Quest List */}
          <div className="quest-list">
            <h2>Quest List</h2>
            {error && <p className="error-message">{error}</p>}
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
                  <p>วันที่ส่ง: {selectedQuest.submissionDate || "ไม่มีข้อมูล"}</p>
                </div>
              </>
            ) : (
              <p>กรุณาเลือกเควสจากรายการด้านซ้าย</p>
            )}
            <div className="action-buttons">
              <button className="cancel-quest" onClick={handleReject}>ไม่อนุมัติ</button>
              <button className="approve-quest" onClick={handleApprove}>อนุมัติ</button>
            </div>
            <input
              type="text"
              className="reason-input"
              placeholder="เหตุผล"
              value={reason}
              onChange={(e) => setReason(e.target.value)}
            />
          </div>
          
        </div>
      </div>
      <Footer />
    </>
  );
};

export default AdminQuestBoard;