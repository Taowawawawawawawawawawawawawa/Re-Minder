import React, { useState, useEffect } from 'react';
import './Myroom.css'; // CSS สำหรับ styling
import { useNavigate } from 'react-router-dom';

const Wardrobe = () => {
  const [selectedItem, setSelectedItem] = useState(null); // เก็บ item ที่ถูกเลือก
  const navigate = useNavigate(); // ใช้สำหรับ navigation
  const [items, setItems] = useState({ costumes: [] }); // เก็บ costumes ที่ดึงมาจาก API
  const [error, setError] = useState(null); // เก็บ error ถ้ามี

  // ฟังก์ชันสำหรับเลือก item
  const selectItem = (item) => {
    setSelectedItem(item);
  };

  // ฟังก์ชันสำหรับกลับไปที่หน้า Myroom
  const handleMyroomClick = () => {
    navigate('/Myroom');
  };

  // ฟังก์ชันสำหรับดึงข้อมูล costumes
  const fetchCostumes = async () => {
    try {
      const response = await fetch('http://localhost:8204/costumes/all'); // API URL สำหรับ costumes
      if (!response.ok) {
        throw new Error(`API error: ${response.status}`);
      }
      const data = await response.json();
      console.log('Fetched costumes:', data); // Debugging log
      setItems({ costumes: data });
    } catch (err) {
      console.error('Error fetching costumes:', err.message);
      setError('Failed to fetch costumes.');
    }
  };

  // ใช้ useEffect เพื่อดึงข้อมูล costumes เมื่อ component ถูก mount
  useEffect(() => {
    fetchCostumes();
  }, []);

  // ฟังก์ชันสำหรับสร้าง URL ของภาพ
  const getFullImageUrl = (path) => {
    return path.trim(); // เอา whitespace ออก
  };

  return (
    <div>
      <div className="navbar">Navbar</div> {/* Mock Navbar */}
      <div className="game-screen">
        {/* Wardrobe Section */}
        <div className="wardrobe">
          <h2>Wardrobe</h2>
          <ul className="wardrobe-list">
            {items.costumes.length > 0 ? (
              items.costumes.map((costume) => (
                <li
                  key={costume.costumeId}
                  onClick={() => selectItem(costume)}
                  className={`wardrobe-item ${
                    selectedItem?.costumeId === costume.costumeId ? 'selected' : ''
                  }`}
                >
                  <img
                    src={getFullImageUrl(costume.costumeFiles)}
                    alt={costume.costumeName}
                    className="wardrobe-icon"
                  />
                  <p>{costume.costumeName}</p>
                </li>
              ))
            ) : (
              <p>No costumes available.</p>
            )}
          </ul>
        </div>

        {/* Character Section */}
        <div className="character">
          <div className="character-eye"></div>
          <div className="character-eye"></div>
          {selectedItem && selectedItem.costumeFiles ? (
            <img
              src={getFullImageUrl(selectedItem.costumeFiles)}
              alt={selectedItem.costumeName}
              className="character-item"
            />
          ) : (
            <p>No item selected.</p>
          )}
        </div>

        {/* Control Buttons */}
        <div className="controls">
          <button className="control-button" onClick={() => setSelectedItem(null)}>
            ถอดเสื้อผ้า
          </button>
          <button className="control-button">ยืนยัน</button>
          <button className="control-button" onClick={handleMyroomClick}>
            กลับห้อง
          </button>
        </div>
      </div>
      <div className="footer">Footer</div> {/* Mock Footer */}
    </div>
  );
};

export default Wardrobe;
