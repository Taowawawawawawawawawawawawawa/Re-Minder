import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import SlimeGif from '../../images/Slime.GIF';
import shop_bg from '../../images/shop_bg.png';

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

    const pageStyle = {
        background: `url(${shop_bg}) no-repeat center center fixed`,
        backgroundSize: 'cover',
        display: 'flex',
        justifyContent: 'flex-end',
        alignItems: 'center',
        height: '100vh',
        paddingRight: '5%',
        overflow: 'hidden',
    };

    const settingsContainerStyle = {
        backgroundColor: 'hsla(44, 56%, 84%, 0.8)',
        border: '4px solid #6D4C41',
        borderRadius: '15px',
        padding: '30px',
        width: '800px',
        height: '70%',
        textAlign: 'center',
        boxShadow: '0 6px 12px rgba(0, 0, 0, 0.3)',
        fontSize: '18px',
    };

    const soundControlStyle = {
        display: 'flex',
        alignItems: 'center',
        gap: '15px',
        marginBottom: '25px',
        width: '100%',
        maxWidth: '500px',
    };

    const soundButtonStyle = {
        background: 'none',
        border: 'none',
        fontSize: '22px',
        cursor: 'pointer',
    };

    const settingsTitleStyle = {
        position: 'absolute',
        top: '12%',
        right: '24%',
        color: 'white',
        fontWeight: 'bold',
        padding: '10px',
        textAlign: 'center',
        marginBottom: '20px',
        fontSize: '32px',
        marginTop: '20px',
    };

    const avatarStyle = {
        position: 'absolute',
        left: '12%',
        top: '40%',
        width: '500px',
        height: '500px',
        borderRadius: '10%',
    };

    const modalBackgroundStyle = {
        position: 'fixed',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        background: 'rgba(0, 0, 0, 0.5)',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
    };

    const modalContainerStyle = {
        backgroundColor: '#EDEDED',
        border: '3px solid #6D4C41',
        borderRadius: '15px',
        padding: '25px',
        width: '600px',
        boxShadow: '0 6px 12px rgba(0, 0, 0, 0.3)',
    };

    const inputStyle = {
        width: '100%',
        padding: '10px',
        fontSize: '18px',
        marginTop: '8px',
        border: '2px solid #6D4C41',
        borderRadius: '8px',
    };

    const modalButtonsStyle = {
        display: 'flex',
        justifyContent: 'space-between',
        marginTop: '25px',
    };

    const saveButtonStyle = {
        padding: '12px 20px',
        fontSize: '18px',
        border: 'none',
        borderRadius: '8px',
        cursor: 'pointer',
        backgroundColor: '#A1887F',
        color: '#FFF',
    };

    const cancelButtonStyle = {
        padding: '12px 20px',
        fontSize: '18px',
        border: 'none',
        borderRadius: '8px',
        cursor: 'pointer',
        backgroundColor: '#5D4037',
        color: '#FFF',
    };

    return (
        <>
            <Navbar />
            <div style={pageStyle}>
                <h2 style={settingsTitleStyle}>Setting</h2>
                <div className="welcome-avatar" style={{ marginLeft: '50px', marginBottom: '-125px' }}>
                    <img src={SlimeGif} alt="Avatar" style={avatarStyle} />
                </div>
                <div style={settingsContainerStyle}>
                    <div style={soundControlStyle}>
                        <label htmlFor="sound">Sound</label>
                        <button style={soundButtonStyle}>🔊</button>
                        <input type="range" id="sound" name="sound" />
                    </div>
                    <div className="dropdown-control" style={{ marginBottom: '20px' }}>
                        <label>Theme</label>
                        <select style={{ width: '100%', padding: '10px', fontSize: '18px', border: '2px solid #6D4C41', borderRadius: '8px' }}>
                            <option>Default</option>
                            <option>Dark</option>
                            <option>Light</option>
                        </select>
                    </div>
                    <div className="dropdown-control" style={{ marginBottom: '20px' }}>
                        <label>Button</label>
                        <select style={{ width: '100%', padding: '10px', fontSize: '18px', border: '2px solid #6D4C41', borderRadius: '8px' }}>
                            <option>Style 1</option>
                            <option>Style 2</option>
                        </select>
                    </div>
                    <button style={{ width: '100%', padding: '15px', backgroundColor: '#A1887F', color: '#FFF', fontSize: '18px', cursor: 'pointer', borderRadius: '8px', marginTop: '15px' }} onClick={handleEditClick}>
                        แก้ไขข้อมูลส่วนตัว
                    </button>
                    <button style={{ width: '100%', padding: '15px', backgroundColor: '#5D4037', color: '#FFF', fontSize: '18px', cursor: 'pointer', borderRadius: '8px', marginTop: '15px' }} onClick={handleLogout}>
                        Logout
                    </button>
                </div>

                {showEditModal && (
                    <div style={modalBackgroundStyle}>
                        <div style={modalContainerStyle}>
                            <label>
                                Name:
                                <input type="text" placeholder="ใส่ชื่อของคุณ" style={inputStyle} />
                            </label>
                            <label>
                                Date of Birth:
                                <input type="text" placeholder="dd/mm/yyyy" style={inputStyle} />
                            </label>
                            <label>
                                MBTI:
                                <select style={inputStyle}>
                                    <option value="">Select MBTI</option>
                                    <option value="INTJ">INTJ</option>
                                    <option value="ENTP">ENTP</option>
                                    <option value="INTP">INTP</option>
                                    <option value="ENTJ">ENTJ</option>
                                    <option value="ESTP">ESTP</option>
                                    <option value="ESFP">ESFP</option>
                                    <option value="ISTP">ISTP</option>
                                    <option value="ISFP">ISFP</option>
                                    <option value="ESTJ">ESTJ</option>
                                    <option value="ISTJ">ISTJ</option>
                                    <option value="ISFJ">ISFJ</option>
                                    <option value="ESFJ">ESFJ</option>
                                </select>
                            </label>
                            <div style={modalButtonsStyle}>
                                <button style={saveButtonStyle}>แก้ไข</button>
                                <button style={cancelButtonStyle} onClick={handleCancelClick}>ยกเลิก</button>
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
