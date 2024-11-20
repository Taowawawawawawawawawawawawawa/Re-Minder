import React, { useState } from 'react';
import './Setting.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import { useNavigate } from "react-router-dom";
import SlimeGif from '../../images/Slime.GIF';

const Settings = () => {
    const navigate = useNavigate();
    const [showEditModal, setShowEditModal] = useState(false);

    const handleEditClick = () => {
        setShowEditModal(true);
    };
    const handleLogout = () => {
        sessionStorage.clear(); // Clear the session
        navigate("/Welcome"); // Redirect to login page
      };
    const handleCancelClick = () => {
        setShowEditModal(false);
    };

    return (
        <>
            <Navbar />
            <div className="settings-background">
                <h2 className="settings-title">Setting</h2> {/* The title here */}
                <div className="welcome-avatar">
                    <img src={SlimeGif} alt="Avatar" className="avatar-image" />
                </div>
                <div className="settings-container">
                    <div className="sound-control">
                        <label htmlFor="sound">Sound</label>
                        <button className="sound-button">🔊</button>
                        <input type="range" id="sound" name="sound" />
                    </div>
                    <div className="dropdown-control">
                        <label>Theme</label>
                        <select>
                            <option>Default</option>
                            <option>Dark</option>
                            <option>Light</option>
                        </select>
                    </div>
                    <div className="dropdown-control">
                        <label>Button</label>
                        <select>
                            <option>Style 1</option>
                            <option>Style 2</option>
                        </select>
                    </div>
                    <button className="edit-info-button" onClick={handleEditClick}>
                        แก้ไขข้อมูลส่วนตัว
                    </button>
                    <button className="logout-button" onClick={handleLogout} >Logout</button>
                </div>

                {showEditModal && (
                    <div className="modal-background">
                        <div className="modal-container">
                            <label>
                                Name:
                                <input type="text" placeholder="ใส่ชื่อของคุณ" />
                            </label>
                            <label>
                                Date of Birth:
                                <input type="text" placeholder="dd/mm/yyyy" />
                            </label>
                            <label>
                                MBTI:
                                <select>
                                    <option>INTJ</option>
                                    <option>ENTP</option>
                                    <option>INFJ</option>
                                    <option>ENFP</option>
                                </select>
                            </label>
                            <div className="modal-buttons">
                                <button className="save-button">แก้ไข</button>
                                <button className="cancel-button" onClick={handleCancelClick}>ยกเลิก</button>
                            </div>
                        </div>
                    </div>
                )}
            </div>
            <Footer />
        </>
    );
};

export default Settings;
