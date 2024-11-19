import React, { useState, useEffect } from 'react';
import './Shop.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import SlimeGif from '../../images/Slime.GIF';

const Shop = () => {
  const [selectedSection, setSelectedSection] = useState('costume'); // Switch between costume and theme
  const [costumes, setCostumes] = useState([]);
  const [themes, setThemes] = useState([]);
  const [error, setError] = useState(null); // For error handling
  const [selectedAvatar, setSelectedAvatar] = useState(SlimeGif); // State for the current avatar image

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
    setSelectedAvatar(item.costumeFiles || item.frameSpriteArts); // Update the avatar image
  };

  const handleCancel = () => {
    setSelectedAvatar(SlimeGif); // Reset to the default profile picture
  };

  return (
    <>
      <Navbar />
      <div className="shop-avatar">
        <img src={selectedAvatar} alt="Avatar" className="avatar-image" />
      </div>
      <div className="shop-container">
        
        <div className="shop-tabs">
          <button
            className={selectedSection === 'costume' ? 'active' : ''}
            onClick={() => setSelectedSection('costume')}
          >
            Costume
          </button>
          <button
            className={selectedSection === 'theme' ? 'active' : ''}
            onClick={() => setSelectedSection('theme')}
          >
            Theme
          </button>
        </div>

        {error && <div className="error">Error: {error}</div>}

        <div className="shop-shelf">
          {/* Render costumes if the selected section is costume */}
          {selectedSection === 'costume' &&
            costumes.map((costume) => (
              <div
                key={costume.costumeId}
                className="shop-item"
                onClick={() => handleItemClick(costume)}
              >
                <img src={costume.costumeFiles} alt={costume.costumeName} />
                <div className="item-name">{costume.costumeName}</div>
                <div className="item-price">
                  {costume.price} <span className="currency-icon">💎</span>
                </div>
              </div>
            ))}

          {/* Render themes if the selected section is theme */}
          {selectedSection === 'theme' &&
            themes.map((theme) => (
              <div
                key={theme.themeId}
                className="shop-item"
                onClick={() => handleItemClick(theme)}
              >
                <img src={theme.frameSpriteArts} alt="Theme Frame" />
                <div className="item-name">{theme.themeName}</div>
                <div className="item-price">
                  {theme.price} <span className="currency-icon">💎</span>
                </div>
              </div>
            ))}
        </div>

        <div className="shop-buttons">
          <button className="cancel-btn" onClick={handleCancel}>Cancel</button>
          <button className="save-btn">Save</button>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default Shop;
