import React from 'react';
import './Navbar.css';
import { useNavigate } from 'react-router-dom';
import icon from '../../images/IconSlime.PNG';
import Beryle from '../../images/Beryle.PNG';
import Point from '../../images/Point.PNG';
import setting_icon from '../../images/setting.png';


function Navbar() {
  const navigate = useNavigate();

  const handleProfileClick = () => {
    navigate("/home ");
  };

  const handlesettingsClick = () => {
    navigate("/setting");
  };

  const handlePointClick = () => {
    navigate("/Point");
  };

  const handleBeryleClick = () => {
    navigate("/Beryle");
  };

  return (

    
    <div className="navbar">
      <div className="navbar-left">
        <img
          src={icon}
          alt="Profile Icon"
          className="profile-icon"
          onClick={handleProfileClick}
        />
        <span className="navbar-title">บทสนทนาแบบสุ่ม</span>
      </div>
      <div className="navbar-right">
        <div class="navbar-right">
          <div class="currency">
            <img src={Point} alt="Green Gem" class="icon" />
            <span class="currency-amount">100 p</span>
            <button class="add-button" onClick={handlePointClick}>+</button>
          </div>
          <div class="currency">
            <img src={Beryle} alt="Blue Gem" class="icon" />
            <span class="currency-amount">99999</span>
            <button class="add-button" onClick={handleBeryleClick}>+</button>
          </div>
          <div class="counter-container">
            <img src={Beryle} alt="Blue Gem" class="gem-icon" />
            <div class="counter-display">
    <span class="counter-text">99999</span>
  </div>
            <button class="add-button" onClick={handleBeryleClick}>+</button>
          </div>
        </div>

        

        <button className="settings-button" onClick={handlesettingsClick}>
          <img src={setting_icon} alt="settings" />
        </button>
      </div>
    </div>
  );
}

export default Navbar;
