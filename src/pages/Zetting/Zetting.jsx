import React, { useState } from 'react';
import './Zetting.css';

function Zetting() {
    const [soundLevel, setSoundLevel] = useState(50);

    const handleSoundChange = (e) => {
        setSoundLevel(e.target.value);
    };

    return (
        <div className="Zetting-container">
            <div className="Zettings-panel">
                <div className="Zetting-item">
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
                <button className="Zetting-button">Change Theme</button>
                <button className="Zetting-button">Action Button</button>
                <button className="Zetting-button">Edit Profile</button>
                <button className="logout-button">Logout</button>
            </div>
            <div className="contact-admin">Contact Admin</div>
        </div>
    );
}

export default Zetting;
