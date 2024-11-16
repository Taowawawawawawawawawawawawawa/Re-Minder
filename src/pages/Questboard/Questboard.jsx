import React, { useEffect, useState } from 'react';
import './Questboard.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

function Questboard() {
  // State to keep track of the selected difficulty
  const [selectedDifficulty, setSelectedDifficulty] = useState("ง่าย");

  // Function to handle tab click and update selectedDifficulty
  const handleTabClick = (difficulty) => {
    setSelectedDifficulty(difficulty);
  };

  const [quests,setQuest] = useState([]);

  useEffect(()=>{
    const fetchquest = async() => {
      const data = await fetch("http://localhost:3000/");
      const json = await data.json();
      setQuest(json);
    };
    fetchquest();



  },[]);


  return <><Navbar />
    <div className="quest-board">
      <div className="quest-board-container">
        <div className="quest-content">
          <div className="quest-list">
            <h3>Quest List</h3>
            <div className="difficulty-tabs">
              <button
                className={`tab-button ${selectedDifficulty === "ง่าย" ? "active" : ""}`}
                onClick={() => handleTabClick("ง่าย")}
              >
                ง่าย
              </button>
              <button
                className={`tab-button ${selectedDifficulty === "กลาง" ? "active" : ""}`}
                onClick={() => handleTabClick("กลาง")}
              >
                กลาง
              </button>
              <button
                className={`tab-button ${selectedDifficulty === "ยาก" ? "active" : ""}`}
                onClick={() => handleTabClick("ยาก")}
              >
                ยาก
              </button>
            </div>
            {quests.map((quest)=>{
                console.log(quest);
            })}
            <div className='quest-list'>
              <ul className="quest-items">
                {/* Example quest items */}
                <li className="quest-item">
                  <p>อรุณสวัสดิ!</p>
                  <div className="reward">💎 x2</div>
                </li>
                <li className="quest-item">
                  <p>อากาศในวันนี้</p>
                  <div className="reward">💎 x2</div>
                </li>
                <li className="quest-item">
                  <p>กินอะไรหรือยัง?</p>
                  <div className="reward">💎 x2</div>
                </li>
                <li className="quest-item">
                  <p>ขอกำลังใจหน่อย!</p>
                  <div className="status">สำเร็จ!</div>
                </li>
              </ul>
            </div>
          </div>

          <div className="quest-detail">
            <h3>Detail</h3>
            <p className="quest-message">
              {/* Dynamically update the detail based on selected difficulty */}
              {selectedDifficulty === "ง่าย" && "นี่คือภารกิจง่าย! เริ่มต้นด้วยสิ่งเล็กๆ"}
              {selectedDifficulty === "กลาง" && "ภารกิจกลางกำลังรอคุณ! มันจะท้าทายมากขึ้น"}
              {selectedDifficulty === "ยาก" && "ภารกิจยาก! คุณต้องใช้ความพยายามมากขึ้น"}
            </p>
            <input type="text" placeholder="ว่างไว้ให้พ่อดู..." className="response-input" />
            <div className="action-buttons">
              <button className="send-button">จัดส่ง</button>
              <button className="cancel-button">ยกเลิก</button>
            </div>
          </div>
        </div>
      </div>

    </div>
    <Footer /></>
}

export default Questboard;
