import React, { useState } from "react";
import "./Userlist.css";
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const Userlist = () => {
    // Sample data to render the user details
    const users = [
        { id: 1, name: "User Detail (ชื่อ, เบอร์โทรศัพท์, MBTI, ไอเทมต่างๆที่มี)" },
        { id: 2, name: "User Detail (ชื่อ, เบอร์โทรศัพท์, MBTI, ไอเทมต่างๆที่มี)" },
        { id: 3, name: "User Detail (ชื่อ, เบอร์โทรศัพท์, MBTI, ไอเทมต่างๆที่มี)" },
    ];

    const [expandedUserId, setExpandedUserId] = useState(null);

    // Toggle function to open or close the dropdown
    const toggleUserDetails = (userId) => {
        setExpandedUserId(expandedUserId === userId ? null : userId);
    };

    return <><Navbar />
        <div className="user-list">
            <header className="user-list-header">
                <h2>Slime List</h2>
                <div className="search-container">
                    <input type="text" placeholder="example@gmail.com" />
                    <button className="search-button">🔍</button>
                </div>
            </header>
            
            <div className="filter-input">
                <input type="text" placeholder="Filter" />
            </div>
            
            <ul className="user-items">
                {users.map((user) => (
                    <li key={user.id} className="user-item">
                        <div className="user-summary">
                            <span>{user.name}</span>
                            <div className="action-buttons">
                                <button onClick={() => toggleUserDetails(user.id)}>แก้ไข</button>
                                <button>ลบ</button>
                                <button>ระงับ</button>
                            </div>
                        </div>
                        
                        {/* Collapsible user details */}
                        {expandedUserId === user.id && (
                            <div className="user-detail">
                                <div className="information">
                                    <h3>Information</h3>
                                    <p>ชื่อ: John Doe</p>
                                    <p>Email: john@example.com</p>
                                    <p>เบอร์โทร: 123-456-7890</p>
                                    <p>MBTI: INTJ</p>
                                </div>
                                <div className="item">
                                    <h3>Item</h3>
                                    <div className="item-icons">
                                        <img src="/path/to/item1.png" alt="Item 1" />
                                        <img src="/path/to/item2.png" alt="Item 2" />
                                    </div>
                                </div>
                            </div>
                        )}
                    </li>
                ))}
            </ul>
        </div>
        <Footer /></>
};

export default Userlist;
