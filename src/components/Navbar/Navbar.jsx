import React from 'react';
import './Navbar.css';
import { useNavigate } from 'react-router-dom';


function Navbar() {
  const navigate = useNavigate();

  // Function to handle profile icon click
  const handleProfileClick = () => {
    navigate("/home "); // Navigate to home
  };

  const handlesettingsClick = () => {
    navigate("/setting"); // Navigate to setting page
  };

  return (
    <div className="navbar">
      <div className="navbar-left">
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
        <button className="settings-button" onClick={handlesettingsClick}>
          <img src="settings-icon.png" alt="settings" />
        </button>
      </div>
    </div>
  );
}

export default Navbar;
