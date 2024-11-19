import React from "react";
import "./Navbar.css";
import { useNavigate } from "react-router-dom";
import icon from "../../images/IconSlime.PNG";
import Beryle from "../../images/Beryle.PNG";
import Point from "../../images/Point.PNG";
import setting_icon from "../../images/setting.png";

function Navbar() {
  const navigate = useNavigate();

  return (
    <div className="navbar">
      <div className="navbar-left">
        <img
          src={icon}
          alt="Profile Icon"
          className="profile-icon"
          onClick={() => navigate("/home")}
        />
        <span className="navbar-title">บทสนทนาแบบสุ่ม</span>
      </div>
      <div className="navbar-right">
        <div className="currency">
          <img src={Point} alt="Point Icon" className="icon" />
          <span className="currency-amount">100 p</span>
          <button className="add-button" onClick={() => navigate("/Point")}>
            +
          </button>
        </div>
        <div className="currency">
          <img src={Beryle} alt="Beryle Icon" className="icon" />
          <span className="currency-amount">99999</span>
          <button className="add-button" onClick={() => navigate("/Beryle")}>
            +
          </button>
        </div>
        <button
          className="settings-button"
          onClick={() => navigate("/setting")}
        >
          <img src={setting_icon} alt="Settings" />
        </button>
      </div>
    </div>
  );
}

export default Navbar;
