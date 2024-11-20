import React, { useState, useEffect } from "react";
import "./Beryle.css";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/footer";
import { Link } from "react-router-dom";
import BeryleImage from "../../images/Beryle.PNG";

const Beryle = () => {
  const [beryle, setBeryle] = useState([]);
  const [video, setVideo] = useState([]);
  const [error, setError] = useState(null); // For error handling
  const [showConfirmation, setShowConfirmation] = useState(false); // To toggle the purchase confirmation modal

  const handlePurchaseClick = () => {
    setShowConfirmation(true); // Show confirmation modal when purchase button is clicked
  };

  const handleConfirmPurchase = () => {
    setShowConfirmation(false); // Close the modal
    alert('Purchase successful'); // Show purchase success alert
  };

  const handleCancelPurchase = () => {
    setShowConfirmation(false); // Close the modal without purchasing
  };

  // Fetch Beryle offers
  const fetchBeryle = async () => {
    try {
      const response = await fetch("http://localhost:8206/beryl/all");
      if (!response.ok) {
        throw new Error(`Beryl API error: ${response.status}`);
      }
      const data = await response.json();
      setBeryle(data);
    } catch (err) {
      setError(err.message);
    }
  };

  // Fetch Video Ads
  const fetchVideo = async () => {
    try {
      const response = await fetch("http://localhost:8207/ads/all");
      if (!response.ok) {
        throw new Error(`Ads API error: ${response.status}`);
      }
      const data = await response.json();
      setVideo(data);
    } catch (err) {
      setError(err.message);
    }
  };

  useEffect(() => {
    fetchBeryle();
    fetchVideo();
  }, []);

  const videoWithLink = video.find((ad) => ad.adsSrc);

  return (
    <>
      <Navbar />
      <div className="beryl-shop">
        {/* Free Section */}
        <div className="free-section">
          <h2>Free!</h2>
          <div className="free-beryl">
            <img src={BeryleImage} alt="Gem" />
            <p>10</p>
          </div>
          {videoWithLink ? (
            <Link key={videoWithLink.adsId} to={videoWithLink.adsSrc} target="_blank">
              <button>Watch Video</button>
            </Link>
          ) : (
            <p>No video link available</p>
          )}
        </div>

        {/* Offers Section */}
        <div className="offers-section" onClick={handlePurchaseClick}>
          {beryle.length > 0 ? (
            beryle.map((offer) => (
              <div
                key={offer.berylId}
                className={`offer-card ${offer.promo ? "promo" : ""}`}
              >
                <p>{offer.berylAmount} Gems</p>
                <img
                  src={BeryleImage}
                  alt={`Gem - ${offer.berylAmount} Gems`}
                />
                <p>${offer.price}</p>
              </div>
            ))
          ) : (
            <p>No offers available at the moment.</p>
          )}
        </div>
      </div>
      {error && <p className="error-message">{error}</p>} {/* Display error messages */}
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
      <Footer />
    </>
  );
};

export default Beryle;
