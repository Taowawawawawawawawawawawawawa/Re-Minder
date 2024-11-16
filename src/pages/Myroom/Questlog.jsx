import React, { useState } from 'react';
import './Myroom.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import { useNavigate } from 'react-router-dom';

const Questlog = () => {
  // Initial quest data
  const [quests, setQuests] = useState([
    { id: 1, title: 'ขอกำลังใจหน่อย!', detail: 'คำตอบของคุณ: เอาหน้ามาในQ :D', status: 'สำเร็จ!', image: '/path-to-dog-image.jpg', isOpen: false },
    { id: 2, title: 'ถ่ายรูปปท้องฟ้าตอนเย็นให้ดูหน่อยสิ', detail: 'รูปถ่ายสวยงามตอนเย็น!', status: 'สำเร็จ!', image: '/path-to-sky-image.jpg', isOpen: false },
  ]);

  // Toggle quest details visibility
  const toggleDetails = (id) => {
    setQuests((prevQuests) =>
      prevQuests.map((quest) =>
        quest.id === id ? { ...quest, isOpen: !quest.isOpen } : quest
      )
    );
  };
  const navigate = useNavigate();

  const handleMyroomClick = () => {
    navigate("/Myroom "); 
  };

  const handleWardrobeClick = () => {
    navigate("/Wardrobe"); 
  };

  return <><Navbar />
    <div className="game-screen">
      {/* Quest List Section */}
      <div className="quest-list">
        <h2>Quest List</h2>
        <ul>
          {quests.map((quest) => (
            <li key={quest.id} className="quest-item" onClick={() => toggleDetails(quest.id)}>
              <span>{quest.title}</span>
              <span className="status success">{quest.status}</span>
            </li>
          ))}
        </ul>
      </div>

      {/* Quest Details Section */}
      <div className="quest-details">
        {quests.map(
          (quest) =>
            quest.isOpen && (
              <div key={quest.id} className="quest-detail">
                <h3>ชื่อเควส: {quest.title}</h3>
                <p>{quest.detail}</p>
                <img src={quest.image} alt={quest.title} className="quest-image" />
              </div>
            )
        )}
      </div>


      {/* Wardrobe Button */}
      <button className="button" onClick={handleMyroomClick}>กลับห้อง</button>
      <button className="wardrobe-button" onClick={handleWardrobeClick}>ตู้เสื้อผ้า</button>
    </div>
    <Footer /></>
};

export default Questlog;
