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

    // Fetch all users
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

    // Fetch user inventory
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

    // Fetch item details from the shop microservice
    const fetchItemDetails = async (inventory) => {
        try {
            const [costumes, rewards, themes] = await Promise.all([
                Promise.all(
                    inventory.costumeList.map((id) =>
                        fetch(`http://localhost:8204/costumes/${id}`).then((res) => res.json())
                    )
                ),
                Promise.all(
                    inventory.rewardList.map((id) =>
                        fetch(`http://localhost:8204/rewards/${id}`).then((res) => res.json())
                    )
                ),
                Promise.all(
                    inventory.themeList.map((id) =>
                        fetch(`http://localhost:8204/themes/${id}`).then((res) => res.json())
                    )
                ),
            ]);
            setItems({ costumes, rewards, themes });
        } catch (err) {
            console.error("Failed to fetch item details:", err);
            setError("Failed to fetch item details.");
        }
    };

    // Use effect to fetch users on initial render
    useEffect(() => {
        fetchUsers();
    }, []);

    // Use effect to fetch inventory when a user is expanded
    useEffect(() => {
        if (expandedUserId !== null) {
            fetchUserInventory(expandedUserId);
        }
    }, [expandedUserId]);

    // Handle toggling user details
    const toggleUserDetails = (userId) => {
        if (expandedUserId === userId) {
            setExpandedUserId(null); // Collapse the details
            setUserInventory([]); // Clear inventory when collapsed
            setItems({ costumes: [], rewards: [], themes: [] }); // Clear item details
        } else {
            setExpandedUserId(userId); // Expand the details
        }
    };

    // Get the full image URL (handles imgbb links)
    const getFullImageUrl = (path) => {
        return path.trim(); // Ensure whitespace is removed
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
                                            {/* Costumes */}
                                            {items.costumes.map((costume) => (
                                                <div key={costume.costumeId} className="item-container">
                                                    <img
                                                        src={getFullImageUrl(costume.costumeFiles)}
                                                        alt={costume.costumeName}
                                                        className="item-icon"
                                                        onError={(e) => {
                                                            e.target.src = "https://via.placeholder.com/150"; // Fallback image
                                                        }}
                                                    />
                                                    <p>{costume.costumeName}</p>
                                                </div>
                                            ))}

                                            {/* Rewards */}
                                            {items.rewards.map((reward) => (
                                                <div key={reward.rewardId} className="item-container">
                                                    <img
                                                        src={getFullImageUrl(reward.rewardSpriteArts)}
                                                        alt={reward.rewardName}
                                                        className="item-icon"
                                                        onError={(e) => {
                                                            e.target.src = "https://via.placeholder.com/150"; // Fallback image
                                                        }}
                                                    />
                                                    <p>{reward.rewardName}</p>
                                                </div>
                                            ))}

                                            {/* Themes */}
                                            {items.themes.map((theme) => (
                                                <div key={theme.themeId} className="item-container">
                                                    <img
                                                        src={getFullImageUrl(theme.frameSpriteArts)}
                                                        alt={`Theme ${theme.themeId}`}
                                                        className="item-icon"
                                                        onError={(e) => {
                                                            e.target.src = "https://via.placeholder.com/150"; // Fallback image
                                                        }}
                                                    />
                                                    <p>Theme</p>
                                                </div>
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
