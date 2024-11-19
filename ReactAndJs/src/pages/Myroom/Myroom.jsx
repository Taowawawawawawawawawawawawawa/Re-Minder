import React, { useState } from 'react';
import Navbar from '../../components/Navbar/Navbar'; 
import Footer from '../../components/Footer/footer'; 
import './Myroom.css';
import { useNavigate } from 'react-router-dom';
import SlimeGif from '../../images/Slime.GIF';

function Myroom() {
  const navigate = useNavigate();
  const [selectedAvatar, setSelectedAvatar] = useState(SlimeGif); // Default avatar image
  const handleDiaryClick = () => {
    navigate("/Diary "); 
  };

  const handleQuestlogClick = () => {
    navigate("/Questlog"); 
  };

  const handleWardrobeClick = () => {
    navigate("/Wardrobe"); 
  };

  return <><Navbar />
    
      <div className="game-screen">
        {/* Character */}
        <div className="character">
        <img src={selectedAvatar} alt="Avatar" className="avatar-image" />
        </div>
        
        {/* Thought Bubble 1 */}
        <div className="thought-bubble thought-bubble-left">
          <img src="path-to-quest-log-icon.png" alt="Quest Log" className="bubble-icon" onClick={handleQuestlogClick} />
          <p>quest log</p>
        </div>
  
        {/* Thought Bubble 2 */}
        <div className="thought-bubble thought-bubble-right">
          <img src="path-to-diary-icon.png" alt="Diary" className="bubble-icon" onClick={handleDiaryClick} />
          <p>ไดอารี่ของฉัน</p>
        </div>
  
        {/* Wardrobe Button */}
        <button className="wardrobe-button" onClick={handleWardrobeClick} >
          ตู้เสื้อผ้า
        </button>
      </div>
      <Footer /></>
  };
  
  export default Myroom;

