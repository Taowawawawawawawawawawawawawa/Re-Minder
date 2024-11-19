import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './SignIn.css';
import SlimeGif from '../../images/Slime.GIF';

function SignIn() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const bypassLogin = async () => {
        setError(''); // Clear previous errors
        
            // Try user login
            let response = await fetch('http://localhost:8200/users/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    email: 'johndoe@example.com',
                    password: 'securePassword123'
                })
            });
            

            if (response.ok) {
                const user = await response.json();
                sessionStorage.setItem('role', 'user');
                sessionStorage.setItem('userData', JSON.stringify(user));
                alert(`Welcome, ${user.name}!`);
                navigate('/Home');
                return;
            }
        }
    


    const handleSignIn = async () => {
        setError(''); // Clear previous errors
        try {
            // Try user login
            console.log(JSON.stringify({ email, password }));

            let response = await fetch('http://localhost:8200/users/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password }),
            });

            if (response.ok) {
                const user = await response.json();
                sessionStorage.setItem('role', 'user');
                sessionStorage.setItem('userData', JSON.stringify(user));
                alert(`Welcome, ${user.name}!`);
                navigate('/Home');
                return;
            }

            if (response.status === 401) {
                setError('Invalid password. Please try again.');
                return;
            }

            if (response.status === 404) {
                // Try admin login
                response = await fetch('http://localhost:8201/admins/auth/login', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ email, password }),
                });

                if (response.ok) {
                    const admin = await response.json();
                    sessionStorage.setItem('role', 'admin');
                    sessionStorage.setItem('adminData', JSON.stringify(admin));
                    alert(`Welcome, ${admin.name}!`);
                    navigate('/AdminDashboard');
                    return;
                }

                if (response.status === 401) {
                    setError('Invalid password for admin account.');
                    return;
                }

                if (response.status === 404) {
                    setError('No account found with this email.');
                    return;
                }
            }
        } catch (err) {
            console.error('Login error:', err);
            setError('An error occurred. Please try again later.');
        }
    };

    return (
        <div className="signin-container">
            <h1 className="signin-title">LOGIN</h1>
            <div className="signin-avatar">
                <img src={SlimeGif} alt="Avatar" className="avatar-image" />
            </div>
            <p className="signin-question">จริงๆเหรอ?</p>
            <form className="signin-form" onSubmit={(e) => e.preventDefault()}>
                <label htmlFor="email">EMAIL</label>
                <input
                    type="email"
                    id="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className="signin-input"
                    required
                />
                <label htmlFor="password">PASSWORD</label>
                <input
                    type="password"
                    id="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="signin-input"
                    required
                />
            </form>
            {error && <p className="signin-error">{error}</p>}
            <div className="signin-buttons">
                <button className="signin-button" onClick={handleSignIn}>จริงสิ (Login)</button>
                <button className="signin-button" onClick={() => navigate('/')}>ย้อนกลับ</button>
                <button className="signin-button" onClick={bypassLogin}>Bypass</button>

            </div>
        </div>
    );
}

export default SignIn;
