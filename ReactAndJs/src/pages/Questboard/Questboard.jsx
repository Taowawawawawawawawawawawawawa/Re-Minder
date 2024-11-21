import React, { useEffect, useState } from "react";
import "./Questboard.css";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/footer";
import SlimeGif from '../../images/Slime.GIF';

function Questboard() {
  const [selectedAvatar, setSelectedAvatar] = useState(SlimeGif); // Default avatar image
  const [selectedDifficulty, setSelectedDifficulty] = useState("ง่าย");
  const [quests, setQuests] = useState([]);
  const [selectedQuestId, setSelectedQuestId] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const [textResponse, setTextResponse] = useState(""); // For text submission
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
    (quest) =>
      quest.difficulty ===
      (selectedDifficulty === "ง่าย" ? 1 : selectedDifficulty === "กลาง" ? 2 : 3)
  );

  // Select a quest
  const selectedQuest = quests.find((quest) => quest.questId === selectedQuestId);

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const handleTextResponseChange = (event) => {
    setTextResponse(event.target.value);
  };

  const handleSubmission = async () => {
    if (!selectedQuestId) {
      alert("กรุณาเลือกภารกิจก่อนจัดส่ง");
      return;
    }

    if (selectedQuest.questSubmitMethod === "image") {
      if (!selectedFile) {
        alert("กรุณาเลือกรูปภาพก่อนจัดส่ง");
        return;
      }

      const formData = new FormData();
      formData.append("file", selectedFile);
      formData.append("questId", selectedQuestId);
      formData.append("userId", 1); // Pass a dummy user ID (replace with actual user ID)

      try {
        setUploadStatus("กำลังตรวจสอบ...");
        const response = await fetch(
          "http://localhost:8203/questlogs/submit-image",
          {
            method: "POST",
            body: formData,
          }
        );

        const result = await response.json();
        console.log("Backend Result:", result);

        if (result.status === "success") {
          if (result.questStatus === "SUCCESS") {
            setUploadStatus("เควสสำเร็จ ✅");
          } else if (result.questStatus === "FAILED") {
            setUploadStatus("วัตถุที่พบไม่ตรงกับเป้าหมาย ❌");
          } else if (result.questStatus === "PENDING") {
            setUploadStatus("รอการตรวจสอบโดยผู้ดูแลระบบ");
          } else {
            setUploadStatus("สถานะไม่ทราบ ❌");
          }
        } else {
          setUploadStatus(result.message || "รูปภาพไม่ถูกต้อง ❌");
        }
      } catch (err) {
        console.error("Upload error:", err);
        setUploadStatus(`เกิดข้อผิดพลาด: ${err.message}`);
      }
    } else if (selectedQuest.questSubmitMethod === "text") {
      if (!textResponse.trim()) {
        alert("กรุณาใส่ข้อความก่อนจัดส่ง");
        return;
      }

      const payload = {
        questId: selectedQuestId,
        userId: 1, // Pass a dummy user ID (replace with actual user ID)
        textResponse,
      };

      try {
        setUploadStatus("กำลังส่งข้อความ...");
        const response = await fetch(
          "http://localhost:8203/questlogs/submit-text",
          {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload),
          }
        );

        const result = await response.json();
        console.log("Backend Result:", result);

        if (result.status === "success") {
          setUploadStatus("ส่งข้อความสำเร็จ ✅");
        } else {
          setUploadStatus(result.message || "เกิดข้อผิดพลาด ❌");
        }
      } catch (err) {
        console.error("Submission error:", err);
        setUploadStatus(`เกิดข้อผิดพลาด: ${err.message}`);
      }
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
              ) : filteredQuests.length > 0 ? (
                <ul className="quest-items">
                  {filteredQuests.map((quest) => (
                    <li
                      key={quest.questId}
                      className={`quest-item ${selectedQuestId === quest.questId ? "selected" : ""}`}
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
                  <p>
                    <strong>คำอธิบาย:</strong> {selectedQuest.questDescription}
                  </p>
                  <p>
                    <strong>วิธีการส่งภารกิจ:</strong> {selectedQuest.questSubmitMethod}
                  </p>

                  {selectedQuest.questSubmitMethod === "image" && (
                    <>
                      <input
                        type="file"
                        accept="image/*"
                        onChange={handleFileChange}
                        className="response-input"
                      />
                    </>
                  )}

                  {selectedQuest.questSubmitMethod === "text" && (
                    <textarea
                      placeholder="กรุณาใส่ข้อความตอบกลับของคุณ"
                      value={textResponse}
                      onChange={handleTextResponseChange}
                      className="response-textarea"
                    />
                  )}

                  <div className="action-buttons">
                    <button className="send-button" onClick={handleSubmission}>
                      จัดส่ง
                    </button>
                    <button
                      className="cancel-button"
                      onClick={() => {
                        setSelectedFile(null);
                        setTextResponse("");
                        setUploadStatus(null);
                      }}
                    >
                      ยกเลิก
                    </button>
                  </div>
                  {uploadStatus && (
                    <p
                      className={`upload-status ${uploadStatus.includes("❌") ? "error" : "success"}`}
                    >
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
      <div className="character">
        <img src={selectedAvatar} alt="Avatar" className="avatar-image" />
      </div>
      <Footer />
    </>
  );
}

export default Questboard;