import React, { useState } from 'react';
import Navbar from '../../components/Navbar/Navbar'; 
import Footer from '../../components/Footer/footer'; 
import './Myroom.css';

const Myroom = () => {
    return (
    
      <div className="game-screen">
        {/* Character */}
        <div className="character">
          <div className="character-eye"></div>
          <div className="character-eye"></div>
        </div>
        
        {/* Thought Bubble 1 */}
        <div className="thought-bubble thought-bubble-left">
          <img src="path-to-quest-log-icon.png" alt="Quest Log" className="bubble-icon" />
          <p>quest log</p>
        </div>
  
        {/* Thought Bubble 2 */}
        <div className="thought-bubble thought-bubble-right">
          <img src="path-to-diary-icon.png" alt="Diary" className="bubble-icon" />
          <p>ไดอารี่ของฉัน</p>
        </div>
  
        {/* Wardrobe Button */}
        <button className="wardrobe-button">
          ตู้เสื้อผ้า
        </button>
      </div>
    );
  };
  
  export default Myroom;