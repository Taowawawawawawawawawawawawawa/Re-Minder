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
    const [searchText, setSearchText] = useState("");
    const [editingEmail, setEditingEmail] = useState(null);
    const [editedEmail, setEditedEmail] = useState("");
    const [selectedItem, setSelectedItem] = useState(null); // State for the selected item

    // ฟังก์ชันดึงข้อมูล
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
            await fetchItemDetails(inventory);
        } catch (err) {
            console.error(err);
            setError(err.message);
        }
    };

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

    const updateUserEmail = async (userId, newEmail) => {
        try {
            const response = await fetch(`http://localhost:8201/admins/user/${userId}/update`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email: newEmail }),
            });

            if (!response.ok) {
                throw new Error(`Failed to update email for userId: ${userId}`);
            }

            const updatedUser = await response.json();
            setUsers((prevUsers) =>
                prevUsers.map((user) =>
                    user.userId === userId ? { ...user, email: updatedUser.email } : user
                )
            );
            setEditingEmail(null); // Stop editing mode
            setEditedEmail(""); // Clear email input
            alert("อีเมลถูกอัปเดตสำเร็จ!");
        } catch (err) {
            console.error("Error updating email:", err);
            alert("เกิดข้อผิดพลาดในการอัปเดตอีเมล");
        }
    };

    const toggleUserDetails = (userId) => {
        if (expandedUserId === userId) {
            setExpandedUserId(null);
            setUserInventory([]);
            setItems({ costumes: [], rewards: [], themes: [] });
        } else {
            setExpandedUserId(userId);
        }
    };

    const getFullImageUrl = (path) => {
        return path.trim();
    };

    const showItemDetails = (item) => {
        setSelectedItem(item); // Set item to show in popup
    };

    const closeItemDetails = () => {
        setSelectedItem(null); // Close popup
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    useEffect(() => {
        if (expandedUserId !== null) {
            fetchUserInventory(expandedUserId);
        }
    }, [expandedUserId]);

    const filteredUsers = users
        .filter((user) =>
            user.name.toLowerCase().includes(searchText.toLowerCase()) ||
            (user.email && user.email.toLowerCase().includes(searchText.toLowerCase())) ||
            (user.mbti && user.mbti.toLowerCase().includes(searchText.toLowerCase()))
        )
        .sort((a, b) => {
            const search = searchText.toLowerCase();
            const aPriority =
                a.name.toLowerCase().indexOf(search) !== -1
                    ? a.name.toLowerCase().indexOf(search)
                    : a.email?.toLowerCase().indexOf(search) ?? 
                      a.mbti?.toLowerCase().indexOf(search) ?? Infinity;
            const bPriority =
                b.name.toLowerCase().indexOf(search) !== -1
                    ? b.name.toLowerCase().indexOf(search)
                    : b.email?.toLowerCase().indexOf(search) ?? 
                      b.mbti?.toLowerCase().indexOf(search) ?? Infinity;

            return aPriority - bPriority;
        });

    

    return (
        <>
            <Navbar />
            <div className="user-list">
                <header className="user-list-header">
                    <h2>Slime List</h2>
                    <div className="filter-input">
                        <input
                            type="text"
                            placeholder="Filter by name, email, or MBTI"
                            value={searchText}
                            onChange={(e) => setSearchText(e.target.value)}
                        />
                    </div>
                </header>

                <ul className="user-items">
                {filteredUsers.map((user) => (
                        <li key={user.userId} className="user-item">
                            <div className="user-summary" onClick={() => toggleUserDetails(user.userId)}>
                                <span className="user-name">{user.name}</span>
                                <div className="action-buttons">
                                    {/* Only show edit button for editing the email */}
                                    <button
                                        onClick={(e) => {
                                            e.stopPropagation(); // Prevent toggling user details
                                            setEditingEmail(user.userId); // Enable editing mode
                                            setEditedEmail(user.email); // Set current email to edit
                                        }}
                                    >
                                        แก้ไข
                                    </button>
                                    <button>ลบ</button>
                                    
                                </div>
                            </div>
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
                                            {items.costumes.map((costume) => (
                                                <div
                                                    key={costume.costumeId}
                                                    className="item-container"
                                                    onClick={() => showItemDetails(costume)}
                                                >
                                                    <img
                                                        src={getFullImageUrl(costume.costumeFiles)}
                                                        alt={costume.costumeName}
                                                        className="item-icon"
                                                    />
                                                    <p>{costume.costumeName}</p>
                                                </div>
                                            ))}
                                            {items.rewards.map((reward) => (
                                                <div key={reward.rewardId} className="item-container">
                                                    <img
                                                        src={getFullImageUrl(reward.rewardSpriteArts)}
                                                        alt={reward.rewardName}
                                                        className="item-icon"
                                                    />
                                                    <p>{reward.rewardName}</p>
                                                </div>
                                            ))}
                                            {items.themes.map((theme) => (
                                                <div key={theme.themeId} className="item-container">
                                                    <img
                                                        src={getFullImageUrl(theme.frameSpriteArts)}
                                                        alt={`Theme ${theme.themeId}`}
                                                        className="item-icon"
                                                    />
                                                    <p>Theme</p>
                                                </div>
                                            ))}
                                        </div>
                                    </div>
                                </div>
                            )}
                            {editingEmail === user.userId && (
                                <div className="edit-email">
                                    <input
                                        type="email"
                                        value={editedEmail}
                                        onChange={(e) => setEditedEmail(e.target.value)}
                                    />
                                    <button onClick={() => updateUserEmail(user.userId, editedEmail)}>
                                        บันทึก
                                    </button>
                                    <button onClick={() => setEditingEmail(null)}>ยกเลิก</button>
                                </div>
                            )}
                        </li>
                    ))}
                </ul>
                {selectedItem && (
                    <div className="item-popup">
                        <div className="popup-content">
                            <button className="close-popup" onClick={closeItemDetails}>
                                ✖
                            </button>
                            <h3>{selectedItem.costumeName || selectedItem.rewardName || `Theme ${theme.themeId}`}</h3>
                            <img
                                src={getFullImageUrl(selectedItem.costumeFiles || selectedItem.rewardSpriteArts || selectedItem.frameSpriteArts)}
                                alt="Item Image"
                                className="item-icon"
                            />
                            <p>{selectedItem.description || "No description available."}</p>
                        </div>
                    </div>
                )}
                {error && <p className="error-message">Error: {error}</p>}
            </div>
            <Footer />
        </>
    );
};

export default Userlist;
