import React, { useState, useEffect } from "react";
import "./Userlist.css";
<<<<<<< HEAD
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/footer";
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import { useEffect } from "react";
>>>>>>> 71088e8f0e96955f31d618e8a98a37844e10569f

const Userlist = () => {
    const [user, setUser] = useState([]);
    const [expandedUserId, setExpandedUserId] = useState(null);
    const [error, setError] = useState(null);

    const fetchUser = async () => {
        try {
            const response = await fetch("http://localhost:8200/users/all");
            if (!response.ok) {
                throw new Error(`API error: ${response.status}`);
            }
            const data = await response.json();
            setUser(data);
        } catch (err) {
            setError(err.message);
        }
    };

    useEffect(() => {
        fetchUser();
    }, []);

    const toggleUserDetails = (userId) => {
        setExpandedUserId(expandedUserId === userId ? null : userId);
    };

    return (
        <>
            <Navbar />
            <div className="user-list">
                <header className="user-list-header">
                    <h2>Slime List</h2>
                    <div className="filter-input">
                        <input type="text" placeholder="Filter" />
                    </div>
                </header>

                <ul className="user-items">
                    {user.map((user) => (
                        <li key={user.userId} className="user-item" onClick={() => toggleUserDetails(user.userId)}>
                            {/* ส่วนข้อมูลผู้ใช้ */}
                            <div className="user-summary">
                                <span className="user-name">{user.name}</span>
                                <div className="action-buttons">
                                    <button>แก้ไข</button>
                                    <button>ลบ</button>
                                    <button>ระงับ</button>
                                </div>
                            </div>

                            {/* แสดง Information ด้านล่าง */}
                            {expandedUserId === user.userId && (
                                <div className="user-detail">
                                    <div className="information">
                                        <h3>Information</h3>
                                        <p>ชื่อ: {user.name}</p>
                                        <p>Email: {user.email}</p>
                                        <p>MBTI: {user.mbti}</p>
                                    </div>
                                    <div className="item">
                                        <h3>Item</h3>
                                        <div className="item-icons">
                                            {user.items?.map((item, index) => (
                                                <img
                                                    key={index}
                                                    src={item.icon}
                                                    alt={`Item ${index + 1}`}
                                                    className="item-icon"
                                                />
                                            ))}
                                        </div>
                                    </div>
                                </div>
                            )}
                        </li>
                    ))}
                </ul>
                {error && <p className="error-message">Error: {error}</p>}
            </div>
            <Footer />
        </>
    );
};

export default Userlist;
