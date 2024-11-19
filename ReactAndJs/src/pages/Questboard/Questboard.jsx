import React, { useEffect, useState } from "react";
import "./Questboard.css";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/footer";

function Questboard() {
  const [selectedDifficulty, setSelectedDifficulty] = useState("ง่าย");
  const [quests, setQuests] = useState([]);
  const [selectedQuestId, setSelectedQuestId] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const [uploadStatus, setUploadStatus] = useState(null);

  // Handle difficulty selection
  const handleTabClick = (difficulty) => {
    setSelectedDifficulty(difficulty);
  };

  // Fetch quests data from API
  useEffect(() => {
    const fetchQuests = async () => {
      try {
        setIsLoading(true);
        const response = await fetch(`http://localhost:8202/quests/all`);
        if (!response.ok) {
          throw new Error("Failed to fetch quests");
        }
        const data = await response.json();
        setQuests(data);
        setError(null);
      } catch (err) {
        console.error("Error fetching quests:", err);
        setError(`Failed to fetch quests: ${err.message}`);
      } finally {
        setIsLoading(false);
      }
    };

    fetchQuests();
  }, []);

  // Filter quests based on selected difficulty
  const filteredQuests = quests.filter(
    (quest) => quest.difficulty === (selectedDifficulty === "ง่าย" ? 1 : selectedDifficulty === "กลาง" ? 2 : 3)
  );

  // Select a quest
  const selectedQuest = quests.find((quest) => quest.questId === selectedQuestId);

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const handleFileSubmit = async () => {
    if (!selectedQuestId) {
      alert("กรุณาเลือกภารกิจก่อนจัดส่ง");
      return;
    }

    if (!selectedFile) {
      alert("กรุณาเลือกรูปภาพก่อนจัดส่ง");
      return;
    }

    const formData = new FormData();
    formData.append("file", selectedFile);
    formData.append("questId", selectedQuestId);
    try {
      setUploadStatus("กำลังตรวจสอบ...");
      const response = await fetch(
        "http://localhost:8203/questlogs/submit-image",
        {
          method: "POST",
          body: formData,
        }
      );

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "การตรวจสอบรูปภาพล้มเหลว");
      }

      const result = await response.json();
      setUploadStatus(result.isValid ? "รูปภาพถูกต้อง ✅" : "รูปภาพไม่ถูกต้อง ❌");
    } catch (err) {
      setUploadStatus(`เกิดข้อผิดพลาด: ${err.message}`);
    }
  };

  return (
    <>
      <Navbar />
      <div className="quest-board">
        <div className="quest-board-container">
          <div className="quest-content">
            <div className="quest-list">
              <h3>Quest List</h3>
              <div className="difficulty-tabs">
                {["ง่าย", "กลาง", "ยาก"].map((difficulty) => (
                  <button
                    key={difficulty}
                    className={`tab-button ${
                      selectedDifficulty === difficulty ? "active" : ""
                    }`}
                    onClick={() => handleTabClick(difficulty)}
                  >
                    {difficulty}
                  </button>
                ))}
              </div>

              {isLoading ? (
                <p>กำลังโหลด...</p>
              ) : error ? (
                <p className="error">{error}</p>
              ) : filteredQuests.length > 0 ? (
                <ul className="quest-items">
                  {filteredQuests.map((quest) => (
                    <li
                      key={quest.questId}
                      className={`quest-item ${
                        selectedQuestId === quest.questId ? "selected" : ""
                      }`}
                      onClick={() => setSelectedQuestId(quest.questId)}
                    >
                      <p>{quest.questName}</p>
                    </li>
                  ))}
                </ul>
              ) : (
                <p>ไม่มีภารกิจในขณะนี้</p>
              )}
            </div>

            <div className="quest-detail">
              {selectedQuest ? (
                <>
                  <h3>รายละเอียดของภารกิจ: {selectedQuest.questName}</h3>
                  <p><strong>คำอธิบาย:</strong> {selectedQuest.questDescription}</p>
                  <p><strong>รางวัล Beryl:</strong> {selectedQuest.berylReward}</p>
                  <p><strong>รางวัลคะแนน:</strong> {selectedQuest.pointReward}</p>
                  <p><strong>วิธีการส่งภารกิจ:</strong> {selectedQuest.questSubmitMethod}</p>
                  <p><strong>เวลาที่เหมาะสม:</strong> {selectedQuest.availableTime.join(", ")}</p>
                  <p><strong>MBTI ที่เหมาะสม:</strong> {selectedQuest.suitableMBTI.join(", ")}</p>
                  <p><strong>เป้าหมาย:</strong> {selectedQuest.targetObject}</p>

                  <input
                    type="file"
                    accept="image/*"
                    onChange={handleFileChange}
                    className="response-input"
                  />
                  <div className="action-buttons">
                    <button className="send-button" onClick={handleFileSubmit}>
                      จัดส่ง
                    </button>
                    <button
                      className="cancel-button"
                      onClick={() => {
                        setSelectedFile(null);
                        setUploadStatus(null);
                      }}
                    >
                      ยกเลิก
                    </button>
                  </div>
                  {uploadStatus && (
                    <p className={`upload-status ${uploadStatus.includes("❌") ? "error" : "success"}`}>
                      {uploadStatus}
                    </p>
                  )}
                </>
              ) : (
                <p>กรุณาเลือกภารกิจเพื่อดูรายละเอียด</p>
              )}
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default Questboard;