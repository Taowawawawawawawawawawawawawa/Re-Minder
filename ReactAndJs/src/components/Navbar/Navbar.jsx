import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import icon from "../../images/IconSlime.PNG";
import Beryle from "../../images/Beryle.PNG";
import Point from "../../images/Point.PNG";
import setting_icon from "../../images/setting.png";
import BG from "../../images/BGwood.png";

// Function to play audio
let sharedAudio = null;

function Navbar() {
  const navigate = useNavigate();

  const [userData, setUserData] = useState(null);
  const [role, setRole] = useState("");
  const [loading, setLoading] = useState(true); // Added loading state

  useEffect(() => {
    // Simulate fetching user data
    const storedRole = sessionStorage.getItem("role");
    const storedUserData =
      storedRole === "user"
        ? JSON.parse(sessionStorage.getItem("userData"))
        : JSON.parse(sessionStorage.getItem("adminData"));

    setRole(storedRole);
    setUserData(storedUserData);

    setLoading(false); // Set loading to false after data is fetched
  }, []);

  const handleLogout = () => {
    sessionStorage.clear(); // Clear the session
    navigate("/SignIn"); // Redirect to login page
  };

  // Play audio function
  const playAudio = () => {
    if (!sharedAudio) {
      sharedAudio = new Audio("/audio/GoodEnd.m4a");
      sharedAudio.loop = true;
    }
    sharedAudio.play().catch((err) => console.error("Error playing audio:", err));
  };

  const navbarStyle = {
    background: `url(${BG}), #D9D9D9`,
    width: '100%',
    height: '80px',
    borderRadius: '0',
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    fontFamily: 'Arial, sans-serif',
    color: '#fff',
    zIndex: 100,
    padding: '0 20px',
  };

  const navbarLeftStyle = {
    display: 'flex',
    alignItems: 'center',
  };

  const profileIconWrapperStyle = {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    width: '70px',
    height: '70px',
    borderRadius: '50%',
    border: '5px solid #8b5e3c',
    background: 'linear-gradient(145deg, #d2b48c, #c2a17b)',
    boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
    padding: '5px',
    margin: '0 10px',
  };

  const profileIconStyle = {
    width: '100px',
    height: '100px',
    borderRadius: '100%',
  };

  const navbarTitleStyle = {
    fontSize: '18px',
    backgroundColor: '#f5deb3',
    padding: '8px 15px',
    borderRadius: '20px',
    textAlign: 'center',
  };

  const navbarRightStyle = {
    display: 'flex',
    gap: '20px',
    padding: '0 10px',
  };

  const currencyStyle = {
    height: '50px',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    backgroundColor: '#deb887',
    border: '5px solid #4a2f27',
    borderRadius: '20px',
    padding: '5px 10px',
    boxShadow: 'inset 0px 0px 5px rgba(0, 0, 0, 0.3)',
    gap: '10px',
    minWidth: '150px',
  };

  const iconStyle = {
    width: '40px',
    height: '40px',
    borderRadius: '50%',
    border: '3px solid #4a2f27',
    marginRight: '10px',
  };

  const currencyAmountStyle = {
    fontSize: '1.2rem',
    color: '#4a2f27',
    fontWeight: 'bold',
  };

  const addButtonStyle = {
    backgroundColor: '#4a2f27',
    color: 'white',
    fontSize: '1rem',
    border: 'none',
    borderRadius: '50%',
    width: '30px',
    height: '30px',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    cursor: 'pointer',
  };

  const addButtonHoverStyle = {
    backgroundColor: '#6b4a3e',
  };

  const settingsButtonStyle = {
    background: 'none',
    border: 'none',
    cursor: 'pointer',
  };

  const settingsButtonImageStyle = {
    width: '40px',
    height: '40px',
  };

  // Loading state
  if (loading) {
    return (
      <div style={navbarStyle}>
        <div>Loading...</div>
      </div>
    );
  }

  return (
    <div style={navbarStyle}>
      <div style={navbarLeftStyle}>
        <div style={profileIconWrapperStyle}>
          <img
            src={icon}
            alt="Profile Icon"
            style={profileIconStyle}
            onClick={() => navigate("/home")}
          />
        </div>
        <span style={navbarTitleStyle}>
          {userData
            ? `Welcome, ${role === "user" ? userData.name : userData.adminName}`
            : "บทสนทนาแบบสุ่ม"}
        </span>
      </div>
      <div style={navbarRightStyle}>
        {role === "user" && userData && (
          <div style={currencyStyle}>
            <img src={Point} alt="Point Icon" style={iconStyle} />
            <span style={currencyAmountStyle}>{userData.points} p</span>
            <button
              style={addButtonStyle}
              onClick={() => navigate("/Point")}
              title="Add Points"
            >
              +
            </button>
          </div>
        )}

        {role === "user" && userData && (
          <div style={currencyStyle}>
            <img src={Beryle} alt="Beryle Icon" style={iconStyle} />
            <span style={currencyAmountStyle}>{userData.beryl}</span>
            <button
              style={addButtonStyle}
              onClick={() => navigate("/Beryle")}
              title="Add Beryl"
            >
              +
            </button>
          </div>
        )}

        {/* Play Audio Button */}
        <button
          onClick={playAudio}
          style={{
            ...addButtonStyle,
            backgroundColor: "#4a2f27",
            padding: '8px',
            width: '40px',
            height: '40px',
            boxShadow: '0px 4px 8px rgba(0, 0, 0, 0.2)',
          }}
          title="Play Song"
        >
          🎵
        </button>

        <button
          style={settingsButtonStyle}
          onClick={() => navigate("/setting")}
          title="Settings"
        >
          <img src={setting_icon} alt="Settings" style={settingsButtonImageStyle} />
        </button>
      </div>
    </div>
  );
}

export default Navbar;
