import React, { useState, useEffect } from 'react';
import "./Beryle.css";
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const Beryle = () => {
  // const offers = [
  //   { amount: "10 + 1p", price: "1.0$", promo: true },
  //   { amount: "20 + 5p", price: "2.0$", promo: true },
  //   { amount: "35 + 3p", price: "3.0$", promo: true },
  //   { amount: "83 + 26p", price: "7.0$", promo: true },
  //   { amount: "125 + 59p", price: "12.0$", promo: true },
  //   { amount: "333 + 33p", price: "25.0$", promo: false },
  //   { amount: "590 + 56p", price: "50.0$", promo: true },
  //   { amount: "3594 + 224p", price: "200.0$", promo: true },
  // ];

  const [beryle, setberyle] = useState([]);
  const [error, setError] = useState(null); // For error handling

  const fetchberyle = async () => {
    try {
      const response = await fetch('http://localhost:8206/beryl/all');
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
    // Fetch both costumes and themes on component load
    fetchberyle();
  }, []);


  return <><Navbar />
    <div className="beryl-shop">
      <div className="free-section">
        <h2>Free!</h2>
        <div className="free-beryl">
          <img src="path_to_gem_image" alt="Gem" />
          <p>10</p>
        </div>
        <button>Watch Video</button>
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
    <Footer /></>
};

export default Beryle;
