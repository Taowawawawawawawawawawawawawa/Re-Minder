// Setting.js
import React, { useState } from 'react';

function Setting() {
    const [soundLevel, setSoundLevel] = useState(50);

    const handleSoundChange = (event) => {
        setSoundLevel(event.target.value);
    };

    return (
        <div>
            <button className="setting-button">Change Theme</button>
            <button className="setting-button">Action Button</button>
            <button className="setting-button">Edit Profile</button>
            <button className="logout-button">Logout</button>
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
    );
}

export default Setting;
