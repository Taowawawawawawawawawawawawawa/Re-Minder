import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Welcome.css';

function Welcome() {
    const navigate = useNavigate();

    const handleSignIn = () => {
        navigate('/SignIn');
    };

    const handleSignUp = () => {
        navigate('/SignUp');
    };

    return (
        <div className="welcome-container">
            <div className="welcome-avatar">
                <img src="path/to/avatar.png" alt="Avatar" className="avatar-image" />
            </div>
            <p className="welcome-message">คุณพอจะรู้บ้างมั้ยว่าฉันเป็นใคร?</p>
            <div className="welcome-buttons">
                <button className="welcome-button" onClick={handleSignIn}>
                    รู้สิ (Login)
                </button>
                <button className="welcome-button" onClick={handleSignUp}>
                    ไม่ (Register)
                </button>
            </div>
            <button className="contact-admin">Contact Admin</button>
        </div>
    );
}

export default Welcome;
