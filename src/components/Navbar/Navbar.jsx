import React from 'react';
import './Navbar.css';
import { useNavigate } from 'react-router-dom';

function Navbar() {
  const navigate = useNavigate();

  // Function to handle profile icon click
  const handleProfileClick = () => {
    navigate("/"); // Navigate to home
  };

  const handleSettingsClick = () => {
    navigate("/Setting"); // Navigate to Setting page
  };

  return (
    <div className="navbar">
      <div className="navbar-left">
        {/* Add the onClick event to navigate to home on profile icon click */}
        <img
          src="profile-icon.png"  // Replace with actual path to profile icon
          alt="Profile Icon"
          className="profile-icon"
          onClick={handleProfileClick} 
        />
        <span className="navbar-title">บทสนทนาแบบสุ่ม</span>
      </div>
      <div className="navbar-right">
        <div className="currency">
          <img src="green-gem.png" alt="Green Gem" className="icon" />
          <span>100 p</span>
          <button className="add-button">+</button>
        </div>
        <div className="currency">
          <img src="blue-gem.png" alt="Blue Gem" className="icon" />
          <span>99999</span>
          <button className="add-button">+</button>
        </div>
        <button className="Settings-button" onClick={handleSettingsClick}>
          <img src="Settings-icon.png" alt="Settings" />
        </button>
      </div>
    </div>
  );
}

export default Navbar;
