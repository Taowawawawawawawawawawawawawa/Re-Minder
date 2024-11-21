import React, { useState, useEffect } from 'react';
import './Myroom.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import { useNavigate } from 'react-router-dom';
import SlimeGif from '../../images/Slime.GIF';

const Questlog = () => {
  const [quests, setQuests] = useState([]);
  const [selectedDifficulty, setSelectedDifficulty] = useState('ง่าย'); // Default difficulty
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const [selectedAvatar, setSelectedAvatar] = useState(SlimeGif); // Default avatar image

  // Fetch quests from backend
  useEffect(() => {
    const fetchQuests = async () => {
      try {
        const response = await fetch('http://localhost:8203/questlogs/all'); // URL ของ API ที่เชื่อมกับ quest log
        if (!response.ok) {
          throw new Error('Failed to fetch quests');
        }
        const data = await response.json();
        setQuests(
          data.map((quest) => ({
            ...quest,
            isOpen: false, // Add isOpen to manage detail toggling
          }))
        );
        setLoading(false);
      } catch (err) {
        setError(err.message);
        setLoading(false);
      }
    };
    fetchQuests();
  }, []);

  // Filter quests by selected difficulty
  const filteredQuests = quests.filter(
    (quest) =>
      quest.difficulty === (selectedDifficulty === 'ง่าย' ? 1 : selectedDifficulty === 'กลาง' ? 2 : 3)
  );

  // Map status to Thai
  const getStatusText = (status) => {
    switch (status) {
      case 'SUCCESS':
        return 'สำเร็จ!';
      case 'PENDING':
        return 'รอตรวจ';
      case 'FAILED':
        return 'ไม่ผ่าน';
      default:
        return 'ไม่ทราบสถานะ';
    }
  };

  // Toggle quest details visibility
  const toggleDetails = (id) => {
    setQuests((prevQuests) =>
      prevQuests.map((quest) =>
        quest.id === id ? { ...quest, isOpen: !quest.isOpen } : quest
      )
    );
  };

  // Handle difficulty tab click
  const handleTabClick = (difficulty) => {
    setSelectedDifficulty(difficulty);
  };

  // Navigate to Myroom
  const handleMyroomClick = () => {
    navigate('/Myroom');
  };

  // Navigate to Wardrobe
  const handleWardrobeClick = () => {
    navigate('/Wardrobe');
  };

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error}</p>;
  }

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
                className={`tab-button ${selectedDifficulty === difficulty ? 'active' : ''}`}
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
                <span>{quest.questName}</span>
                <span className={`status ${quest.status}`}>
                  {getStatusText(quest.status)}
                </span>
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
                    <h3>ชื่อเควส: {quest.questName}</h3>
                    <p>คำอธิบาย: {quest.questDescription}</p>
                    {quest.imageUrl && (
                      <img
                        src={`data:image/jpeg;base64,${quest.imageUrl}`} // ใช้ backtick เพื่อรวม Base64 string
                        alt={quest.questName}
                        className="questlog-image"
                      />
                    )}
                    <p>สถานะ: {getStatusText(quest.status)}</p>
                    <p>รางวัล: {quest.berylReward} เบริล, {quest.pointReward} คะแนน</p>
                  </div>
                )
            )
          ) : (
            <div className="questlog-placeholder">
              <h3>โปรดเลือกเควสเพื่อดูรายละเอียด</h3>
            </div>
          )}
        </div>

        {/* Avatar */}
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