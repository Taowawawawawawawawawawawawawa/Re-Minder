import React, { useState } from 'react';
import './Shop.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';


const items = [
  { id: 1, category: 'costume', name: 'หมวกคาวบอย', image: 'cowboy-hat.png', price: 359 },
  { id: 2, category: 'costume', name: 'หมวกหรูหรา', image: 'luxury-hat.png', price: 359 },
  { id: 3, category: 'costume', name: 'หมวกเอ็กซ์พลอเรอร์', image: 'explorer-hat.png', price: 359 },
  { id: 4, category: 'costume', name: 'ชุดแดง', image: 'red-outfit.png', price: 359 },
  { id: 5, category: 'costume', name: 'เนคไทสุดเท่', image: 'cool-tie.png', price: 359 },
  { id: 6, category: 'costume', name: 'โบว์แห่งความตาย', image: 'bow.png', price: 359 },
  { id: 7, category: 'costume', name: 'ผ้าพันคอ', image: 'scarf.png', price: 359 },
  { id: 8, category: 'costume', name: 'ผ้าพันคอน่ารัก', image: 'cute-scarf.png', price: 359 },
  { id: 9, category: 'costume', name: 'ปีก', image: 'wings.png', price: 359 },

];

const Shop = () => {
  const [selectedSection, setSelectedSection] = useState('costume');
  const [selectedItem, setSelectedItem] = useState(null);

  const handleItemClick = (item) => {
    setSelectedItem(item);
    alert(`You selected: ${item.name}`);
  };

  return <><Navbar />
    <div className="shop-container">
      <div className="shop-tabs">
        <button
          className={selectedSection === 'costume' ? 'active' : ''}
          onClick={() => setSelectedSection('costume')}
        >
          costume
        </button>
        <button
          className={selectedSection === 'theme' ? 'active' : ''}
          onClick={() => setSelectedSection('theme')}
        >
          Theme
        </button>
      </div>

      <div className="shop-shelf">
        {items
          .filter(item => item.category === selectedSection)
          .map(item => (
            <div key={item.id} className="shop-item" onClick={() => handleItemClick(item)}>
              <img src={item.image} alt={item.name} />
              <div className="item-name">{item.name}</div>
              <div className="item-price">
                {item.price} <span className="currency-icon">💎</span>
              </div>
            </div>
          ))}
      </div>

      <div className="shop-buttons">
        <button className="cancel-btn">ยกเลิก</button>
        <button className="save-btn">บันทึก</button>
      </div>
    </div>
    <Footer /></>
};

export default Shop;
