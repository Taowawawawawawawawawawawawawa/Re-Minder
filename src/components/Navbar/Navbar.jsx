import React from 'react';
import './Navbar.css';
import { useNavigate } from 'react-router-dom';

function Navbar() {
  const navigate = useNavigate();

  const handleProfileClick = () => {
    navigate('/'); // Navigate to the Profile page (or any component you'd like)
  };

  return (
    <div className="navbar">
      <div className="navbar-left">
        <img src="" className="profile-icon" />
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
        <button className="settings-button">
          <img src="settings-icon.png" alt="Settings" />
        </button>
      </div>
    </div>
  );
}

export default Navbar;
