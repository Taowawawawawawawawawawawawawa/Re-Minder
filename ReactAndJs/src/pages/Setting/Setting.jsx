import React, { useState } from 'react';
import './Setting.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import { useNavigate } from 'react-router-dom';

function Setting() {
    const [soundLevel, setSoundLevel] = useState(50);

    const handleSoundChange = (event) => {
        setSoundLevel(event.target.value);
    };

    const navigate = useNavigate();
    const logOut = () => {
        navigate("/Welcome"); // Navigate to home
      };
    return <><Navbar />
        <div>
            <button className="setting-button">Change Theme</button>
            <button className="setting-button">Action Button</button>
            <button className="setting-button">Edit Profile</button>
            <button className="logout-button" onClick={logOut}>Logout</button>
            <label htmlFor="sound">Sound Level</label>
            <input
                type="range"
                id="sound"
                min="0"
                max="100"
                value={soundLevel}
                onChange={handleSoundChange}
            />
            <span className="sound-level">{soundLevel}</span>
        </div>
        <Footer /></>
}

export default Setting;
