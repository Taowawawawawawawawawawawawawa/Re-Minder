import React, { useState, useEffect } from 'react';
import "./Point.css";
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import Beryle from "../../images/Beryle.PNG";
import PointImage from "../../images/Point.PNG"; // Renamed import to PointImage

const Point = () => {
  const exchanges = new Array(15).fill({
    amount: "300pt",
    icon: PointImage, // Use the renamed variable here
    reward: Beryle,
  });

  const [points, setPoints] = useState(exchanges); // Set initial state to the filled array
  const [error, setError] = useState(null);
  const [showConfirmation, setShowConfirmation] = useState(false); // Added showConfirmation state

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

  const fetchPoints = async () => {
    try {
      const response = await fetch("http://localhost:8204/rewards/all");
      if (!response.ok) {
        throw new Error(`API error: ${response.status}`);
      }
      const data = await response.json();
      // Fill points array if it's smaller than the desired length
      setPoints(prevPoints => {
        // Repeat the data until the array length reaches 15
        const desiredLength = 15;
        const repeatedPoints = [...prevPoints, ...data];
        return repeatedPoints.slice(0, desiredLength);
      });
    } catch (err) {
      setError(err.message);
    }
  };

  useEffect(() => {
    fetchPoints(); // Fetch points data when component mounts
  }, []); // Empty dependency array to fetch only once

  return (
    <>
      <Navbar />
      <div className="point-exchange">
        <div className="header">
          <h2>แลกพ้อยได้ที่นี่เลย</h2>
        </div>
        <div className="exchange-grid">
          {error ? (
            <p className="error-message">{error}</p>
          ) : (
            points.map((point, index) => (
              <div key={index} className="exchange-card" onClick={handlePurchaseClick}>
                <img
                  className="exchange-icon"
                  src={point.icon} // Centered icon image
                  alt="Ticket"
                />
                <div className="exchange-info">
                  <p className="points">{point.amount}</p>
                  <img
                    className="reward-icon"
                    src={point.reward} // Image for the gem icon
                    alt="Reward Gem"
                  />
                </div>
              </div>
            ))
          )}
        </div>
      </div>
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

export default Point;
