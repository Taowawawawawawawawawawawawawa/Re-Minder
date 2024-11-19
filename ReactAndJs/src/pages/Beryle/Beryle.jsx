import React, { useState, useEffect } from "react";
import "./Beryle.css";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/footer";
import { Link } from "react-router-dom";

const Beryle = () => {
  const [beryle, setberyle] = useState([]);
  const [error, setError] = useState(null); // For error handling

  const fetchberyle = async () => {
    try {
      const response = await fetch("http://localhost:8206/beryl/all");
      if (!response.ok) {
        throw new Error(`Costume API error: ${response.status}`);
      }
      const data = await response.json();
      setberyle(data);
    } catch (err) {
      setError(err.message);
    }
  };

  useEffect(() => {
    fetchberyle();
  }, []);

  const [video, setvideo] = useState([]);

  const fetchvideo = async () => {
    try {
      const response = await fetch("http://localhost:8207/ads/all");
      if (!response.ok) {
        throw new Error(`API error: ${response.status}`);
      }
      const data = await response.json();
      setvideo(data);
    } catch (err) {
      setError(err.message);
    }
  };

  useEffect(() => {
    fetchvideo();
  }, []);
  const videoWithLink = video.find(videos => videos.adsSrc);
  return (
    <>
      <Navbar />
      <div className="beryl-shop">
        <div className="free-section">
          <h2>Free!</h2>
          <div className="free-beryl">
            <img src="path_to_gem_image" alt="Gem" />
            <p>10</p>
          </div>
          {videoWithLink ? (
      <Link key={videoWithLink.adsId} to={videoWithLink.adsSrc}>
        <button>Watch Video</button>
      </Link>
    ) : (
      <p>No video link available</p>
    )}
        </div>

        <div className="offers-section">
          {beryle.map((beryle) => (
            <div
              key={beryle.berylId}
              className={`offer-card ${beryle.promo ? "promo" : ""}`}
            >
              <p>{beryle.berylAmount}</p>
              <img src="path_to_gem_image" alt="Gem" />
              <p>{beryle.price}</p>
            </div>
          ))}
        </div>
      </div>
      <Footer />
    </>
  );
};

export default Beryle;
