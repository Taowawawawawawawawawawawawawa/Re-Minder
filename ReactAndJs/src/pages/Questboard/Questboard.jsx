import React, { useEffect, useState } from 'react';
import './Questboard.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

function Questboard() {
  const [selectedDifficulty, setSelectedDifficulty] = useState("ง่าย");
  const [quests, setQuests] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const [uploadStatus, setUploadStatus] = useState(null);

  // Handle difficulty selection
  const handleTabClick = (difficulty) => {
    setSelectedDifficulty(difficulty);
  };

  // Fetch quests based on difficulty
  useEffect(() => {
    const fetchQuests = async () => {
      try {
        setIsLoading(true);
        const response = await fetch(`/api/quests?difficulty=${selectedDifficulty}`);
        if (!response.ok) {
          throw new Error("Failed to fetch quests");
        }
        const data = await response.json();
        setQuests(data);
        setError(null);
      } catch (err) {
        setError(err.message);
      } finally {
        setIsLoading(false);
      }
    };

    fetchQuests();
  }, [selectedDifficulty]);

  // Handle file selection
  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  // Handle file submission
  const handleFileSubmit = async () => {
    if (!selectedFile) {
      alert("กรุณาเลือกรูปภาพก่อนจัดส่ง");
      return;
    }

    const formData = new FormData();
    formData.append("file", selectedFile);

    try {
      setUploadStatus("กำลังตรวจสอบ...");
      const response = await fetch(`/quest/submit`, {
        method: "POST",
        body: formData,
      });

      if (!response.ok) {
        throw new Error("การตรวจสอบรูปภาพล้มเหลว");
      }

      const result = await response.json();
      setUploadStatus(result.isValid ? "รูปภาพถูกต้อง ✅" : "รูปภาพไม่ถูกต้อง ❌");
    } catch (err) {
      setUploadStatus("เกิดข้อผิดพลาดในการตรวจสอบรูปภาพ");
    }
  };

  return (
    <>
      <Navbar />
      <div className="quest-board">
        <div className="quest-board-container">
          <div className="quest-content">
            {/* Quest List */}
            <div className="quest-list">
              <h3>Quest List</h3>
              <div className="difficulty-tabs">
                {["ง่าย", "กลาง", "ยาก"].map((difficulty) => (
                  <button
                    key={difficulty}
                    className={`tab-button ${selectedDifficulty === difficulty ? "active" : ""}`}
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
              ) : quests.length > 0 ? (
                <ul className="quest-items">
                  {quests.map((quest) => (
                    <li key={quest.id} className="quest-item">
                      <p>{quest.name}</p>
                      <div className="reward">{quest.reward}</div>
                    </li>
                  ))}
                </ul>
              ) : (
                <p>ไม่มีภารกิจในขณะนี้</p>
              )}
            </div>

            {/* Quest Detail */}
            <div className="quest-detail">
              <h3>Detail</h3>
              <p className="quest-message">
                {selectedDifficulty === "ง่าย" && "นี่คือภารกิจง่าย! เริ่มต้นด้วยสิ่งเล็กๆ"}
                {selectedDifficulty === "กลาง" && "ภารกิจกลางกำลังรอคุณ! มันจะท้าทายมากขึ้น"}
                {selectedDifficulty === "ยาก" && "ภารกิจยาก! คุณต้องใช้ความพยายามมากขึ้น"}
              </p>

              {/* Image Upload Section */}
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
              {uploadStatus && <p className="upload-status">{uploadStatus}</p>}
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default Questboard;