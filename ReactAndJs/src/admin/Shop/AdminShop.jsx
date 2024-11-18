import React, { useState , useEffect } from "react";
import "./AdminShop.css";
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const AdminShop = () => {
  const [formData, setFormData] = useState({
    itemName: "",
    image: "",
    price: "",
    category: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("New Item Created:", formData);
    alert("Item created successfully!");
  };

  const categories = [
    { name: "หมวกคาวบอย", price: "320pt", icon: "path_to_icon1" },
    { name: "หมวกหรูหรา", price: "360pt", icon: "path_to_icon2" },
    { name: "หมวกนักสำรวจ", price: "280pt", icon: "path_to_icon3" },
    { name: "ชุดแต่ง", price: "520pt", icon: "path_to_icon4" },
    { name: "แอคเซสสุดเก๋", price: "460pt", icon: "path_to_icon5" },
    { name: "โบว์แห่งความตาย", price: "340pt", icon: "path_to_icon6" },
    { name: "ผ้าคลุม", price: "420pt", icon: "path_to_icon7" },
    { name: "ปีก", price: "580pt", icon: "path_to_icon8" },
  ];

  const [costumes, setCostumes] = useState([]);
  const [themes, setThemes] = useState([]);
  const [selectedSection, setSelectedSection] = useState('costume');
  const fetchCostumes = async () => {
    try {
      const response = await fetch('http://localhost:8204/costumes/all');
      if (!response.ok) {
        throw new Error(`Costume API error: ${response.status}`);
      }
      const data = await response.json();
      setCostumes(data);
    } catch (err) {
      setError(err.message);
    }
  };

  const fetchThemes = async () => {
    try {
      const response = await fetch('http://localhost:8204/themes/all');
      if (!response.ok) {
        throw new Error(`Theme API error: ${response.status}`);
      }
      const data = await response.json();
      setThemes(data);
    } catch (err) {
      setError(err.message);
    }
  };

  useEffect(() => {
    // Fetch both costumes and themes on component load
    fetchCostumes();
    fetchThemes();
  }, []);

  const handleItemClick = (item) => {
    alert(`You selected: ${item.costumeName || item.themeName}`);
  };

  return <><Navbar />
    <div className="admin-shop">
      <div className="form-section">
        <h2>New Item</h2>
        <form onSubmit={handleSubmit}>
          <label>
            ชื่อไอเทม:
            <input
              type="text"
              name="itemName"
              value={formData.itemName}
              onChange={handleChange}
              required
            />
          </label>
          <label>
            อัพรูปภาพ (jpg/png):
            <input
              type="file"
              name="image"
              accept="image/png, image/jpeg"
              onChange={(e) =>
                setFormData({ ...formData, image: e.target.files[0]?.name })
              }
              required
            />
          </label>
          <label>
            ราคา:
            <input
              type="number"
              name="price"
              value={formData.price}
              onChange={handleChange}
              required
            />
          </label>
          <label>
            หมวดหมู่:
            <select
              name="category"
              value={formData.category}
              onChange={handleChange}
              required
            >
              <option value="">เลือกหมวดหมู่</option>
              <option value="costume">Costume</option>
              <option value="theme">Theme</option>
            </select>
          </label>
          <button type="submit">สร้างไอเทม</button>
        </form>
      </div>

      <div className="items-section">
        <div className="tabs">
          <button 
          className={selectedSection === 'costume' ? 'active' : ''}
            onClick={() => setSelectedSection('costume')}>
              Costume
              </button>
          <button 
          className={selectedSection === 'theme' ? 'active' : ''}
            onClick={() => setSelectedSection('theme')}>
              Theme
               </button>
        </div>
        <div className="items-grid">
        {selectedSection === 'costume' &&
            costumes.map((costume) => (
              <div
                key={costume.costumeId}
                className="item-card"
                onClick={() => handleItemClick(costume)}
              >
                <img src={costume.costumeFiles} alt={costume.costumeName} />
                <div className="item-name">{costume.costumeName}</div>
                <div className="item-price">
                  {costume.price} <span className="currency-icon">💎</span>
                </div>
              </div>
            ))}
            
            {selectedSection === 'theme' &&
            themes.map((theme) => (
              <div
                key={theme.themeId}
                className="item-card"
                onClick={() => handleItemClick(theme)}
              >
                <img src={theme.frameSpriteArts} alt="Theme Frame" />
                <div className="item-name">{theme.themeId}</div>
                <div className="item-price">
                  {theme.price} <span className="currency-icon">💎</span>
                </div>
              </div>
            ))}
        </div>
      </div>
    </div>
    <Footer /></>
};

export default AdminShop;
