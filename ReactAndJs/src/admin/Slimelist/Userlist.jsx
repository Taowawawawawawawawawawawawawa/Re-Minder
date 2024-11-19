import React, { useState, useEffect } from "react";
import "./Userlist.css";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/footer";

const Userlist = () => {
    const [users, setUsers] = useState([]);
    const [expandedUserId, setExpandedUserId] = useState(null);
    const [userInventory, setUserInventory] = useState([]);
    const [error, setError] = useState(null);

    const fetchUsers = async () => {
        try {
            const response = await fetch("http://localhost:8200/users/all");
            if (!response.ok) {
                throw new Error(`API error: ${response.status}`);
            }
            const data = await response.json();
            setUsers(data);
        } catch (err) {
            setError(err.message);
        }
    };

    const fetchUserInventory = async (userId) => {
        try {
            const response = await fetch(`http://localhost:8208/inventory/users/${userId}`);
            if (!response.ok) {
                throw new Error(`Failed to fetch inventory for userId: ${userId}`);
            }
            const data = await response.json();
            console.log("Inventory Data:", data); // ดูข้อมูล inventory
            setUserInventory(data);
        } catch (err) {
            console.error(err);
            setError(err.message);
        }
    };
    

    useEffect(() => {
        fetchUsers();
    }, []);
    useEffect(() => {
        if (expandedUserId !== null) {
            fetchUserInventory(expandedUserId); // เรียก fetchUserInventory เมื่อ expandedUserId เปลี่ยน
        }
    }, [expandedUserId]);

    const toggleUserDetails = (userId) => {
        if (expandedUserId === userId) {
            setExpandedUserId(null); // Collapse the details
            setUserInventory([]); // Clear inventory when collapsed
        } else {
            setExpandedUserId(userId); // Expand the details
        }
    };
    const getFullImageUrl = (costumeList) => {
        return costumeList.startsWith("http") 
            ? costumeList 
            : `http://localhost:8200${costumeList}`;
    };
    
    const handleInvenClick = (inven) => {
        setExpandedUserId(inven); // Set the selected quest
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
                    {users.map((user) => (
                        <li key={user.userId} className="user-item" onClick={() => handleInvenClick(user.userId)}>
                            {/* User Summary */}
                            <div className="user-summary">
                                <span className="user-name">{user.name}</span>
                                <div className="action-buttons">
                                    <button>แก้ไข</button>
                                    <button>ลบ</button>
                                    <button>ระงับ</button>
                                </div>
                            </div>

                            {/* User Details */}
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
                                        {userInventory.map((inv, index) => (
                                            inv.costumeList ? (
                                                <img
                                                    key={inv.inventoryId}
                                                    src={getFullImageUrl(inv.costumeList)}
                                                    alt={`Item ${index + 1}`}
                                                    className="item-icon"
                                                    onError={(e) => {
                                                        e.target.src = "https://via.placeholder.com/150"; // แสดง placeholder
                                                    }}
                                                />
                                            ) : (
                                                <span key={inv.inventoryId}>No image available</span>
                                            )
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
