import React, { useState } from 'react';
import './Myroom.css';
import Navbar from '../../components/Navbar/Navbar'; 
import Footer from '../../components/Footer/footer'; 
import { useNavigate } from 'react-router-dom';

const Wardrobe = () => {
  const [selectedItem, setSelectedItem] = useState(null);
  const navigate = useNavigate();

  // Wardrobe items data
  const items = [
    { id: 1, name: 'Hat', image: '/path-to-hat-image.png' },
    { id: 2, name: 'Shirt', image: '/path-to-shirt-image.png' },
    { id: 3, name: 'Wings', image: '/path-to-wings-image.png' },
  ];

  const selectItem = (item) => {
    setSelectedItem(item);
  };

  const handleMyroomClick = () => {
    navigate('/Myroom');
  };

  return (
    <>
      <Navbar />
      <div className="game-screen">
        {/* Wardrobe Section */}
        <div className="wardrobe">
          <h2>Wardrobe</h2>
          <ul className="wardrobe-list">
            {items.map((item) => (
              <li
                key={item.id}
                className={`wardrobe-item ${
                  selectedItem?.id === item.id ? 'selected' : ''
                }`}
                onClick={() => selectItem(item)}
              >
                <img src={item.image} alt={item.name} className="wardrobe-icon" />
              </li>
            ))}
          </ul>
        </div>

        {/* Character */}
        <div className="character">
          <div className="character-eye"></div>
          <div className="character-eye"></div>
          {selectedItem && <img src={selectedItem.image} alt={selectedItem.name} className="character-item" />}
        </div>

        {/* Control Buttons */}
        <div className="controls">
          <button className="control-button" onClick={() => setSelectedItem(null)}>ถอดเสื้อผ้า</button>
          <button className="control-button">ยืนยัน</button>
          <button className="control-button" onClick={handleMyroomClick}>กลับห้อง</button>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default Wardrobe;
