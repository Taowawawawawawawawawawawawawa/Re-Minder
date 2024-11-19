import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './SignIn.css';
import SlimeGif from '../../images/Slime.GIF';

function SignIn() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSignIn = () => {
        navigate('/Home');
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
            <h1 className="signin-title">LOGIN</h1>
            <div className="signin-avatar">
                <img src={SlimeGif} alt="Avatar" className="avatar-image" />
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

                <button className="signin-button" onClick={handleSignIn}>จริงสิ (Login)</button>
                <button className="signin-button" onClick={handleBack}>ย้อนกลับ</button>
            </div>


            <div className="signup-button-container">
                <button className="signup-button" onClick={handleSignUp}>คิดว่าจำผิดนะ (Register)</button>
            </div>

            <button className="contact-admin">Contact Admin</button>
        </div>
    );
}

export default SignIn;
