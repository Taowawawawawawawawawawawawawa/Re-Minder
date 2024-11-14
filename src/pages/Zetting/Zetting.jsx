import React, { useState } from 'react';
import './Zetting.css';

const Zetting = () => {
    const [soundLevel, setSoundLevel] = useState(50);

    const handleSoundChange = (event) => {
        setSoundLevel(event.target.value);
    };

    return (
        <div>
            <button className="Zetting-button">Change Theme</button>
            <button className="Zetting-button">Action Button</button>
            <button className="Zetting-button">Edit Profile</button>
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
};

export default Zetting;