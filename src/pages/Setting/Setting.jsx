import React, { useState } from 'react';
import './Setting.css';

function Setting() {
    const [soundLevel, setSoundLevel] = useState(50);

    const handleSoundChange = (e) => {
        setSoundLevel(e.target.value);
    };

    return (
        <div className="setting-container">

            <div className="settings-panel">
                <div className="setting-item">
                    <label htmlFor="sound">Sound</label>
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
                <button className="setting-button">Theme</button>
                <button className="setting-button">Button</button>
                <button className="setting-button">แก้ไขข้อมูลส่วนตัว</button>
                <button className="logout-button">Logout</button>
            </div>

            <div className="contact-admin">Contact Admin</div>
        </div>
    );
}

export default Setting;
