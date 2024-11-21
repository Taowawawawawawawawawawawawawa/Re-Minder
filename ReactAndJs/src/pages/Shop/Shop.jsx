import React, { useState, useEffect } from 'react';
import './Shop.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import SlimeGif from '../../images/Slime.GIF';
import SlimeWizard from '../../images/Slime-Wizard.PNG'; // Add the image for wizard
import SlimePrincess from '../../images/Slime-princess.PNG'; // Add the image for princess

const Shop = () => {
  const [selectedSection, setSelectedSection] = useState('costume'); // Switch between costume and theme
  const [costumes, setCostumes] = useState([]);
  const [themes, setThemes] = useState([]);
  const [error, setError] = useState(null); // For error handling
  const [selectedAvatar, setSelectedAvatar] = useState(SlimeGif); // Default avatar image
  const [showConfirmation, setShowConfirmation] = useState(false); // To toggle the purchase confirmation modal

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
          {/* Show Purchase button only when the avatar is changed */}
          {selectedAvatar !== SlimeGif && (
            <button className="purchase-btn" onClick={handlePurchaseClick}>
              Purchase
            </button>
          )}

          {/* Always show Cancel button */}
          <button className="cancel-btn" onClick={handleCancel}>Cancel</button>
        </div>

        {/* Confirmation Modal */}
        {showConfirmation && (
          <div className="confirmation-modal">
            <div className="confirmation-container">
              <p>Are you sure you want to purchase this item?</p>
              <div className="confirmation-buttons">
                <button onClick={handleConfirmPurchase}>Yes</button>
                <button onClick={handleCancelPurchase}>No</button>
              </div>
            </div>
          </div>
        )}
      </div>
      <Footer />
    </>
  );
};

export default Shop;
