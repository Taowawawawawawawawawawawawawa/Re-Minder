import React, { useState, useEffect } from 'react';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import SlimeGif from '../../images/Slime.GIF';
import SlimeWizard from '../../images/Slime-Wizard.PNG'; // Add the image for wizard
import SlimePrincess from '../../images/Slime-princess.PNG'; // Add the image for princess
import shop_bg from '../../images/shop_bg.png';
import BG2 from '../../images/BGwood.png'; // Import background image

const Shop = () => {
  const [selectedSection, setSelectedSection] = useState('costume'); // Switch between costume and theme
  const [costumes, setCostumes] = useState([]);
  const [themes, setThemes] = useState([]);
  const [error, setError] = useState(null); // For error handling
  const [selectedAvatar, setSelectedAvatar] = useState(SlimeGif); // Default avatar image
  const [showConfirmation, setShowConfirmation] = useState(false); // To toggle the purchase confirmation modal

  // Fetch costumes from API
  const fetchCostumes = async () => {
    try {
      const response = await fetch('http://localhost:8204/costumes/all');
      if (!response.ok) {
        throw new Error(`Costume API error: ${response.status}`);
      }
      const data = await response.json();
      setCostumes(data);
    } catch (err) {
      setError('Failed to load costumes. Please try again later.');
    }
  };

  // Fetch themes from API
  const fetchThemes = async () => {
    try {
      const response = await fetch('http://localhost:8204/themes/all');
      if (!response.ok) {
        throw new Error(`Theme API error: ${response.status}`);
      }
      const data = await response.json();
      setThemes(data);
    } catch (err) {
      setError('Failed to load themes. Please try again later.');
    }
  };

  useEffect(() => {
    // Fetch both costumes and themes on component load
    fetchCostumes();
    fetchThemes();
  }, []);

  const handleItemClick = (item) => {
    alert(`You selected: ${item.costumeName || item.themeName}`);

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
  };

  const handlePurchaseClick = () => {
    setShowConfirmation(true); // Show confirmation modal when purchase button is clicked
  };

  const handleConfirmPurchase = async () => {
    if (!selectedAvatar || !costumes.length) {
      alert("Please select a costume to purchase.");
      return;
    }

    const user = JSON.parse(sessionStorage.getItem("userData")); // Retrieve the user's data from sessionStorage
    const userId = user.userId;

    // Extract the filename and derive a keyword from it
    const selectedAvatarFilename = selectedAvatar.split('/').pop().toLowerCase();
    const keyword = selectedAvatarFilename.split('-')[1]?.split('.')[0]; // Extract "wizard" or "princess"

    console.log("Selected Avatar Filename:", selectedAvatarFilename);
    console.log("Derived Keyword:", keyword);

    // Find the matching costume based on keyword
    const selectedCostume = costumes.find((costume) => {
      const costumeFilename = costume.costumeFiles.split('/').pop().toLowerCase();
      const costumeNameKeyword = costume.costumeName.toLowerCase();
      return (
        costumeFilename.includes(keyword) || // Match keyword in filename
        costumeNameKeyword.includes(keyword) // Match keyword in costumeName
      );
    });

    if (!selectedCostume) {
      alert("Selected costume is invalid. Please ensure the costume data matches correctly.");
      return;
    }

    try {
      // Check if the user already owns this costume
      const ownsCostumeResponse = await fetch(
        `http://localhost:8208/inventory/${userId}/ownsItem/costume/${selectedCostume.costumeId}`
      );
      if (!ownsCostumeResponse.ok) {
        console.error("Failed to verify if the user owns this costume:", await ownsCostumeResponse.text());
        throw new Error("Failed to verify if the user owns this costume");
      }
      const alreadyOwns = await ownsCostumeResponse.json();
      if (alreadyOwns) {
        alert("You already own this costume.");
        return;
      }

      // Check user's Beryl balance
      const userBerylResponse = await fetch(`http://localhost:8200/users/${userId}/beryl`);
      if (!userBerylResponse.ok) {
        throw new Error("Failed to fetch user's Beryl balance");
      }
      const userBeryl = await userBerylResponse.json();
      if (userBeryl < selectedCostume.price) {
        alert("You do not have enough Beryl to purchase this item.");
        return;
      }

      // Deduct Beryl from user's account
      const deductBerylResponse = await fetch(
        `http://localhost:8200/users/${userId}/deductBeryl?amount=${selectedCostume.price}`,
        { method: "PUT" }
      );
      if (!deductBerylResponse.ok) {
        throw new Error("Failed to deduct Beryl from user's account");
      }

      // Call the shop service to confirm the purchase
      const purchaseResponse = await fetch(
        `http://localhost:8204/costumes/purchase/${selectedCostume.costumeId}?userId=${userId}`,
        { method: "POST" }
      );
      if (!purchaseResponse.ok) {
        throw new Error(await purchaseResponse.text());
      }

      // Add the costume to the user's inventory
      const inventoryResponse = await fetch(
        `http://localhost:8208/inventory/${userId}/addItem/costume`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ costumeId: selectedCostume.costumeId }), // Send as an object with key-value
        }
      );
      if (!inventoryResponse.ok) {
        throw new Error(await inventoryResponse.text());
      }


      setShowConfirmation(false); // Close the confirmation modal
      alert("Purchase successful and added to your inventory!");
    } catch (error) {
      console.error("Error during purchase:", error);
      alert(`Failed to complete the purchase: ${error.message}`);
    }
  };


  const handleCancelPurchase = () => {
    setShowConfirmation(false); // Close the modal without purchasing
  };

  const pageStyle = {
    background: `url(${shop_bg}) no-repeat center center fixed`, // Ensure it is centered
    backgroundSize: 'cover', // Ensure the image covers the entire page
    minHeight: '100vh', // Set minimum height to ensure it covers the viewport height
    margin: 0,
    padding: 0,
    overflow: 'hidden',
  };

  const avatarStyle = {
    position: 'absolute',
    left: '20px',
    top: '75%',
    transform: 'translateY(-50%)',
  };

  const avatarImageStyle = {
    width: '450px',
    height: 'auto',
  };

  const shopContainerStyle = {
    fontFamily: 'Arial, sans-serif',
    background : `url(${BG2}), #D9D9D9`,
    border: '2px solid #8b5a2b',
    borderRadius: '10px',
    padding: '20px',
    boxShadow: '3px 3px 10px rgba(0, 0, 0, 0.2)',
    maxWidth: '600px',
    margin: '50px auto',
  };

  const shopTabsStyle = {
    display: 'flex',
    justifyContent: 'center',
    marginBottom: '15px',
  };

  const shopTabButtonStyle = {
    backgroundColor: '#8b5a2b',
    color: '#fff',
    border: 'none',
    padding: '10px 20px',
    margin: '0 5px',
    borderRadius: '5px',
    cursor: 'pointer',
    fontSize: '1em',
    transition: 'background-color 0.3s ease',
  };

  const activeTabButtonStyle = {
    backgroundColor: '#a5673f',
  };

  const shopShelfStyle = {
    display: 'flex',
    flexWrap: 'wrap',
    gap: '15px',
    justifyContent: 'center',
  };

  const shopItemStyle = {
    backgroundColor: '#fff',
    border: '1px solid #c69a6b',
    borderRadius: '10px',
    padding: '10px',
    width: '120px',
    textAlign: 'center',
    cursor: 'pointer',
    boxShadow: '2px 2px 8px rgba(0, 0, 0, 0.1)',
    transition: 'transform 0.2s ease, box-shadow 0.2s ease',
  };

  const shopItemHoverStyle = {
    transform: 'scale(1.05)',
    boxShadow: '4px 4px 12px rgba(0, 0, 0, 0.2)',
  };

  const shopItemImageStyle = {
    width: '100px',
    height: 'auto',
    marginBottom: '5px',
  };

  const itemNameStyle = {
    fontSize: '0.9em',
    marginBottom: '5px',
    color: '#333',
  };

  const itemPriceStyle = {
    fontSize: '0.8em',
    color: '#5d3b1a',
  };

  const currencyIconStyle = {
    marginLeft: '3px',
  };

  const shopButtonsStyle = {
    display: 'flex',
    justifyContent: 'space-between',
    marginTop: '20px',
  };

  const cancelButtonStyle = {
    backgroundColor: '#8b5a2b',
    color: '#fff',
    border: 'none',
    padding: '10px 20px',
    borderRadius: '5px',
    cursor: 'pointer',
    fontSize: '1em',
    transition: 'background-color 0.3s ease',
  };

  const purchaseButtonStyle = {
    backgroundColor: '#8b5a2b',
    color: '#fff',
    border: 'none',
    padding: '10px 20px',
    borderRadius: '5px',
    cursor: 'pointer',
    fontSize: '1em',
    transition: 'background-color 0.3s ease',
  };

  const errorStyle = {
    color: 'red',
    fontSize: '0.9em',
    marginBottom: '15px',
    textAlign: 'center',
  };

  const confirmationModalStyle = {
    position: 'fixed',
    top: 0,
    left: 0,
    width: '100%',
    height: '100%',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    zIndex: 10,
  };

  const confirmationContainerStyle = {
    backgroundColor: 'white',
    padding: '20px',
    borderRadius: '10px',
    boxShadow: '0 0 10px rgba(0, 0, 0, 0.3)',
    textAlign: 'center',
  };

  const confirmationButtonsStyle = {
    marginTop: '15px',
  };

  const confirmationButtonStyle = {
    backgroundColor: '#8b5a2b',
    color: 'white',
    border: 'none',
    padding: '10px 20px',
    borderRadius: '5px',
    cursor: 'pointer',
    margin: '0 5px',
  };

  return (
    <div style={pageStyle}> {/* Apply the background to the root div */}
    <>
      <Navbar />
      <div style={avatarStyle}>
        <img src={selectedAvatar} alt="Avatar" style={avatarImageStyle} />
      </div>
      <div style={shopContainerStyle}>
        <div style={shopTabsStyle}>
          <button
            style={{ ...shopTabButtonStyle, ...(selectedSection === 'costume' ? activeTabButtonStyle : {}) }}
            onClick={() => setSelectedSection('costume')}
          >
            Costume
          </button>
          <button
            style={{ ...shopTabButtonStyle, ...(selectedSection === 'theme' ? activeTabButtonStyle : {}) }}
            onClick={() => setSelectedSection('theme')}
          >
            Theme
          </button>
        </div>

        {error && <div style={errorStyle}>{error}</div>}

        <div style={shopShelfStyle}>
          {/* Render costumes if the selected section is costume */}
          {selectedSection === 'costume' &&
            costumes.map((costume) => (
              <div
                key={costume.costumeId}
                style={shopItemStyle}
                onClick={() => handleItemClick(costume)}
              >
                <img src={costume.costumeFiles} alt={costume.costumeName} style={shopItemImageStyle} />
                <div style={itemNameStyle}>{costume.costumeName}</div>
                <div style={itemPriceStyle}>
                  {costume.price} <span style={currencyIconStyle}>💎</span>
                </div>
              </div>
            ))}

          {/* Render themes if the selected section is theme */}
          {selectedSection === 'theme' &&
            themes.map((theme) => (
              <div
                key={theme.themeId}
                style={shopItemStyle}
                onClick={() => handleItemClick(theme)}
              >
                <img src={theme.frameSpriteArts} alt="Theme Frame" style={shopItemImageStyle} />
                <div style={itemNameStyle}>{theme.themeName}</div>
                <div style={itemPriceStyle}>
                  {theme.price} <span style={currencyIconStyle}>💎</span>
                </div>
              </div>
            ))}
        </div>

        <div style={shopButtonsStyle}>
          {/* Show Purchase button only when the avatar is changed */}
          {selectedAvatar !== SlimeGif && (
            <button style={purchaseButtonStyle} onClick={handlePurchaseClick}>
              Purchase
            </button>
          )}

          {/* Always show Cancel button */}
          <button style={cancelButtonStyle} onClick={handleCancel}>Cancel</button>
        </div>

        {/* Confirmation Modal */}
        {showConfirmation && (
          <div style={confirmationModalStyle}>
            <div style={confirmationContainerStyle}>
              <p>Are you sure you want to purchase this item?</p>
              <div style={confirmationButtonsStyle}>
                <button style={confirmationButtonStyle} onClick={handleConfirmPurchase}>Yes</button>
                <button style={confirmationButtonStyle} onClick={handleCancelPurchase}>No</button>
              </div>
            </div>
          </div>
        )}
      </div>
      
      <Footer />
    </>
    </div>
  );
};

export default Shop;
