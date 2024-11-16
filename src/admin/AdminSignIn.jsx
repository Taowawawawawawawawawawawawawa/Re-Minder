import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';


function AdminSignIn() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSignIn = () => {
        // Implement SignIn logic
    };

    const handleBack = () => {
        navigate('/');
    };

    const handleSignUp = () => {
        navigate('/SignUp');
    };

    return (
        <div className="signin-container">
            <h1 className="signin-title">SignIn</h1>
            <div className="signin-avatar">
                <img src="path/to/avatar.png" alt="Avatar" className="avatar-image" />
            </div>
            <p className="signin-question">จริงๆเหรอ?</p>
            <form className="signin-form">
                <label htmlFor="email">EMAIL</label>
                <input
                    type="email"
                    id="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className="signin-input"
                />
                <label htmlFor="password">PASSWORD</label>
                <input
                    type="password"
                    id="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="signin-input"
                />
            </form>
            <div className="signin-buttons">
                <button className="signin-button" onClick={handleSignIn}>จริงสิ (SignIn)</button>
                <button className="signin-button" onClick={handleBack}>ย้อนกลับ</button>
                <button className="signin-button" onClick={handleSignUp}>คิดว่าจำผิดนะ (SignUp)</button>
            </div>
            <button className="contact-admin">Contact Admin</button>
        </div>
    );
}

export default AdminSignIn;
