import React, { useState, useEffect } from 'react';
import './Myroom.css';
import { useNavigate } from 'react-router-dom';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import SlimeGif from '../../images/Slime.GIF';
import wardrobe from '../../images/wardrobe.png';
import SlimeWizard from '../../images/Slime-Wizard.PNG'; // Add the image for wizard
import SlimePrincess from '../../images/Slime-princess.PNG'; // Add the image for princess

const Wardrobe = () => {
  const [selectedSection, setSelectedSection] = useState('costume'); // Switch between costume and theme
  const [costumes, setCostumes] = useState([]);
  const [themes, setThemes] = useState([]);
  const [error, setError] = useState(null); // For error handling
  const [selectedAvatar, setSelectedAvatar] = useState(SlimeGif); // Default avatar image
  const [selectedItem, setSelectedItem] = useState(null); // To track the selected item
  const navigate = useNavigate();

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
    setSelectedItem(item);

    // Check the selected costume/theme and set the corresponding avatar
    if (item.costumeName === 'Wizard Hat') {
      setSelectedAvatar(SlimeWizard); // Set to Slime-Wizard.PNG if Wizard Hat is selected
    } else if (item.costumeName === 'Princess Dress') {
      setSelectedAvatar(SlimePrincess); // Set to Slime-princess.PNG if Princess is selected
    } else {
      setSelectedAvatar(item.costumeFiles || item.frameSpriteArts);
    }
  };

  const handleCancel = () => {
    setSelectedAvatar(SlimeGif); // Reset to the default profile picture
    setSelectedItem(null); // Deselect item
  };

  const handleMyroomClick = () => {
    navigate('/Myroom'); // Navigate back to Myroom
  };

  return (
    <>
      <Navbar />
      <div className="game-screen">
        <div className="wardrobe-container">
          <h2>Wardrobe</h2>

          {/* Error Message */}
          {error && <div className="error">Error: {error}</div>}


          {/* Render costumes if the selected section is costume */}
          <div className="wardrobe-shelf">
            {selectedSection === 'costume' && costumes.length > 0 ? (
              costumes.map((costume) => (
                <div
                  key={costume.costumeId}
                  className={`wardrobe-item ${selectedItem?.costumeId === costume.costumeId ? 'selected' : ''}`}
                  onClick={() => handleItemClick(costume)}
                >
                  <img
                    src={costume.costumeFiles}
                    alt={costume.costumeName}
                    className="wardrobe-icon"
                  />
                  <p>{costume.costumeName}</p>
                </div>
              ))
            ) : (
              <p>No costumes available.</p>
            )}

            {/* Render themes if the selected section is theme */}
            {selectedSection === 'theme' && themes.length > 0 ? (
              themes.map((theme) => (
                <div
                  key={theme.themeId}
                  className={`wardrobe-item ${selectedItem?.themeId === theme.themeId ? 'selected' : ''}`}
                  onClick={() => handleItemClick(theme)}
                >
                  <img
                    src={theme.frameSpriteArts}
                    alt={theme.themeName}
                    className="wardrobe-icon"
                  />
                  <p>{theme.themeName}</p>
                </div>
              ))
            ) : (
              <p></p>
            )}
          </div>
          </div>

          {/* Avatar display */}
          <div className="character">
            <img src={selectedAvatar} alt="Avatar" className="avatar-image" />
          </div>

          {/* Control Buttons */}
          <div className="controls">
            <button className="control-button1" onClick={handleCancel}>
              Remove Costume
            </button>
            <button className="control-button2" onClick={handleMyroomClick}>
              Back to Myroom
            </button>
          </div>
        </div>

      {/* Wardrobe Icon */}
      <img src={wardrobe} alt="Wardrobe" className="wardrobe-mainicon" />
      <Footer />
    </>
  );
};

export default Wardrobe;
