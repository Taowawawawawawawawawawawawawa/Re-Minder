import React, { useState, useEffect } from "react";
import "./Userlist.css";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/footer";

const Userlist = () => {
    const [users, setUsers] = useState([]);
    const [expandedUserId, setExpandedUserId] = useState(null);
    const [userInventory, setUserInventory] = useState([]);
    const [items, setItems] = useState({ costumes: [], rewards: [], themes: [] });
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
            const response = await fetch(`http://localhost:8208/inventory/user/${userId}`);
            if (!response.ok) {
                throw new Error(`Failed to fetch inventory for userId: ${userId}`);
            }
            const inventory = await response.json();
            setUserInventory(inventory);

            // Fetch item details based on inventory IDs
            await fetchItemDetails(inventory);
        } catch (err) {
            console.error(err);
            setError(err.message);
        }
    };

    const fetchItemDetails = async (inventory) => {
        try {
            const [costumes, rewards, themes] = await Promise.all([
                Promise.all(inventory.costumeList.map((id) =>
                    fetch(`http://localhost:8204/costumes/${id}`).then((res) => res.json())
                )),
                Promise.all(inventory.rewardList.map((id) =>
                    fetch(`http://localhost:8204/rewards/${id}`).then((res) => res.json())
                )),
                Promise.all(inventory.themeList.map((id) =>
                    fetch(`http://localhost:8204/themes/${id}`).then((res) => res.json())
                )),
            ]);
            setItems({ costumes, rewards, themes });
        } catch (err) {
            console.error("Failed to fetch item details:", err);
            setError("Failed to fetch item details.");
        }
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    useEffect(() => {
        if (expandedUserId !== null) {
            fetchUserInventory(expandedUserId); // Fetch inventory when a user is expanded
        }
    }, [expandedUserId]);

    const toggleUserDetails = (userId) => {
        if (expandedUserId === userId) {
            setExpandedUserId(null); // Collapse the details
            setUserInventory([]); // Clear inventory when collapsed
            setItems({ costumes: [], rewards: [], themes: [] }); // Clear item details
        } else {
            setExpandedUserId(userId); // Expand the details
        }
    };

    const getFullImageUrl = (path) => {
        return path.startsWith("http")
            ? path
            : `http://localhost:8204${path}`;
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
                        <li key={user.userId} className="user-item" onClick={() => toggleUserDetails(user.userId)}>
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
                                        <h3>Items</h3>
                                        <div className="item-icons">
                                            {/* <h4>Costumes</h4> */}
                                            {items.costumes.map((costume) => (
                                                <img
                                                    key={costume.costumeId}
                                                    src={getFullImageUrl(costume.costumeFiles)}
                                                    alt={costume.costumeName}
                                                    className="item-icon"
                                                    onError={(e) => {
                                                        e.target.src = "https://via.placeholder.com/150"; // Placeholder
                                                    }}
                                                />
                                            ))}
                                            {/* <h4>Rewards</h4> */}
                                            {items.rewards.map((reward) => (
                                                <img
                                                    key={reward.rewardId}
                                                    src={getFullImageUrl(reward.rewardSpriteArts)}
                                                    alt={reward.rewardName}
                                                    className="item-icon"
                                                    onError={(e) => {
                                                        e.target.src = "https://via.placeholder.com/150"; // Placeholder
                                                    }}
                                                />
                                            ))}
                                            {/* <h4>Themes</h4> */}
                                            {items.themes.map((theme) => (
                                                <img
                                                    key={theme.themeId}
                                                    src={getFullImageUrl(theme.frameSpriteArts)}
                                                    alt={`Theme ${theme.themeId}`}
                                                    className="item-icon"
                                                    onError={(e) => {
                                                        e.target.src = "https://via.placeholder.com/150"; // Placeholder
                                                    }}
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
