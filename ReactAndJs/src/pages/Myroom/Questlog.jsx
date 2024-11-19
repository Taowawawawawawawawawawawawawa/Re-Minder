import React, { useState } from 'react';
import './Myroom.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import { useNavigate } from 'react-router-dom';
import Dad from '../../images/Pond_Dad.png'; // Correctly import the image
import SlimeGif from '../../images/Slime.GIF';

const Questlog = () => {
  // Initial quest data
  const [quests, setQuests] = useState([
    { id: 1, title: 'ขอกำลังใจหน่อย!', detail: 'คำตอบของคุณ: เอาหน้ามาในQ :D', status: 'สำเร็จ!', image: Dad, difficulty: 1, isOpen: false },
    { id: 2, title: 'ถ่ายรูปปท้องฟ้าตอนเย็นให้ดูหน่อยสิ', detail: 'รูปถ่ายสวยงามตอนเย็น!', status: 'สำเร็จ!', image: Dad, difficulty: 2, isOpen: false },
  ]);

  const [selectedDifficulty, setSelectedDifficulty] = useState('ง่าย'); // Default difficulty

  // Filter quests by selected difficulty
  const filteredQuests = quests.filter(
    (quest) => quest.difficulty === (selectedDifficulty === 'ง่าย' ? 1 : selectedDifficulty === 'กลาง' ? 2 : 3)
  );

  // Toggle quest details visibility
  const toggleDetails = (id) => {
    setQuests((prevQuests) =>
      prevQuests.map((quest) =>
        quest.id === id ? { ...quest, isOpen: !quest.isOpen } : quest
      )
    );
  };

  const navigate = useNavigate();
  const [selectedAvatar, setSelectedAvatar] = useState(SlimeGif); // Default avatar image
  const handleMyroomClick = () => {
    navigate('/Myroom');
  };

  const handleWardrobeClick = () => {
    navigate('/Wardrobe');
  };

  // Handle difficulty tab click
  const handleTabClick = (difficulty) => {
    setSelectedDifficulty(difficulty);
  };

  return (
    <>
      <Navbar />
      <div className="game-screen">
        {/* Quest List Section */}
        <div className="questlog-list">
          <h2>Quest List</h2>
          <div className="difficulty-tabs">
            {['ง่าย', 'กลาง', 'ยาก'].map((difficulty) => (
              <button
                key={difficulty}
                className={`tab-button ${selectedDifficulty === difficulty ? 'active' : ''
                  }`}
                onClick={() => handleTabClick(difficulty)}
              >
                {difficulty}
              </button>
            ))}
          </div>
          <ul>
            {filteredQuests.map((quest) => (
              <li
                key={quest.id}
                className="questlog-item"
                onClick={() => toggleDetails(quest.id)}
              >
                <span>{quest.title}</span>
                <span className="status success">{quest.status}</span>
              </li>
            ))}
          </ul>
        </div>

        {/* Quest Details Section */}
        <div className="questlog-details">
          <h2>Quest Detail</h2>
          {quests.some((quest) => quest.isOpen) ? (
            quests.map(
              (quest) =>
                quest.isOpen && (
                  <div key={quest.id} className="questlog-detail">
                    <h3>ชื่อเควส: {quest.title}</h3>
                    <p>{quest.detail}</p>
                    <img src={quest.image} alt={quest.title} className="questlog-image" />
                  </div>
                )
            )
          ) : (
            <div className="questlog-placeholder">
              <h3>โปรดเลือกเควสเพื่อดูรายละเอียด</h3>
            </div>
          )}
        </div>
        <div className="character">
          <img src={selectedAvatar} alt="Avatar" className="avatar-image" />
        </div>
        {/* Buttons */}
        <button className="questlog-button" onClick={handleMyroomClick}>
          กลับห้อง
        </button>
        <button className="wardrobe-button" onClick={handleWardrobeClick}>
          ตู้เสื้อผ้า
        </button>
      </div>
      <Footer />
    </>
  );
};

export default Questlog;
