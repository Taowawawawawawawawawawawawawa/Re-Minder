import React, { useEffect, useState } from "react";
import "./Navbar.css";
import { useNavigate } from "react-router-dom";
import icon from "../../images/IconSlime.PNG";
import Beryle from "../../images/Beryle.PNG";
import Point from "../../images/Point.PNG";
import setting_icon from "../../images/setting.png";

function Navbar() {
  const navigate = useNavigate();

  const [userData, setUserData] = useState(null);
  const [role, setRole] = useState("");

  useEffect(() => {
    // Fetch user or admin data from sessionStorage
    const storedRole = sessionStorage.getItem("role");
    const storedUserData =
      storedRole === "user"
        ? JSON.parse(sessionStorage.getItem("userData"))
        : JSON.parse(sessionStorage.getItem("adminData"));

    setRole(storedRole);
    setUserData(storedUserData);
  }, []);

  const handleLogout = () => {
    sessionStorage.clear(); // Clear the session
    navigate("/SignIn"); // Redirect to login page
  };

  return (
    <div className="navbar">
      <div className="navbar-left">
        <div className="profile-icon-wrapper">
          <img
            src={icon}
            alt="Profile Icon"
            className="profile-icon"
            onClick={() => navigate("/home")}
          />
        </div>
        <span className="navbar-title">
          {userData
            ? `Welcome, ${role === "user" ? userData.name : userData.adminName}`
            : "บทสนทนาแบบสุ่ม"}
        </span>
      </div>
      <div className="navbar-right">
        {/* Display Points for Users */}
        {role === "user" && userData && (
          <div className="currency">
            <img src={Point} alt="Point Icon" className="icon" />
            <span className="currency-amount">{userData.points} p</span>
            <button
              className="add-button"
              onClick={() => navigate("/Point")}
              title="Add Points"
            >
              +
            </button>
          </div>
        )}

        {/* Display Beryl for Users */}
        {role === "user" && userData && (
          <div className="currency">
            <img src={Beryle} alt="Beryle Icon" className="icon" />
            <span className="currency-amount">{userData.beryl}</span>
            <button
              className="add-button"
              onClick={() => navigate("/Beryle")}
              title="Add Beryl"
            >
              +
            </button>
          </div>
        )}

        {/* Admin Controls */}
        {role === "admin" && (
          <div className="admin-controls">
            <button
              className="admin-button"
              onClick={() => navigate("/AdminDashboard")}
            >
              Dashboard
            </button>
          </div>
        )}

        {/* Settings */}
        <button
          className="settings-button"
          onClick={() => navigate("/setting")}
          title="Settings"
        >
          <img src={setting_icon} alt="Settings" />
        </button>

        {/* Logout
        <button className="logout-button" onClick={handleLogout} title="Logout">
          Logout
        </button> */}
      </div>
    </div>
  );
}

export default Navbar;
