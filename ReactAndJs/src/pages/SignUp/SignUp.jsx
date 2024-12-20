import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './SignUp.css';
import SlimeGif from '../../images/Slime.GIF';

function SignUp() {
    const navigate = useNavigate();
    const [form, setForm] = useState({
        name: '',
        age: '',
        email: '',
        password: '',
        confirmPassword: '',
        mbti: ''
    });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSignUp = () => {
        alert(`created account successfully!`);
        navigate('/Home');
    };

    const handleBack = () => {
        navigate('/');
    };

    return (
        <div className="signup-container">
            <h1 className="signup-title">REGISTER</h1>
            <div className="signup-avatar">
                <img src={SlimeGif} alt="Avatar" className="avatar-image" />
            </div>
            <p className="signup-question">ถ้างั้น ฉันขอใช้ตัวตนร่วมกับคุณได้มั้ย?</p>
            <form className="signup-form">
                <div className="form-row">
                    <div className="form-group">
                        <label htmlFor="name">NAME</label>
                        <input
                            type="text"
                            id="name"
                            name="name"
                            value={form.name}
                            onChange={handleChange}
                            className="signup-input"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="age">AGE</label>
                        <input
                            type="text"
                            id="age"
                            name="age"
                            value={form.age}
                            onChange={handleChange}
                            className="signup-input"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="email">EMAIL</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={form.email}
                        onChange={handleChange}
                        className="signup-input"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">PASSWORD</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={form.password}
                        onChange={handleChange}
                        className="signup-input"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="confirmPassword">CONFIRM PASSWORD</label>
                    <input
                        type="password"
                        id="confirmPassword"
                        name="confirmPassword"
                        value={form.confirmPassword}
                        onChange={handleChange}
                        className="signup-input"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="mbti">MBTI</label>
                    <select
                        id="mbti"
                        name="mbti"
                        value={form.mbti}
                        onChange={handleChange}
                        className="signup-select"
                    >
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
                </div>
            </form>
            <div className="signup-buttons">
                <button className="signup-button" onClick={handleSignUp}>ได้สิ (Register)</button>
                <button className="signup-button" onClick={handleBack}>ย้อนกลับ</button>
            </div>
            <button className="contact-admin">Contact Admin</button>
        </div>

    );
}

export default SignUp;
