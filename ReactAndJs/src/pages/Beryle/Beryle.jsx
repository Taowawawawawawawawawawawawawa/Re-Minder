import React from "react";
import "./Beryle.css";
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const Beryle = () => {
  const offers = [
    { amount: "10 + 1p", price: "1.0$", promo: true },
    { amount: "20 + 5p", price: "2.0$", promo: true },
    { amount: "35 + 3p", price: "3.0$", promo: true },
    { amount: "83 + 26p", price: "7.0$", promo: true },
    { amount: "125 + 59p", price: "12.0$", promo: true },
    { amount: "333 + 33p", price: "25.0$", promo: false },
    { amount: "590 + 56p", price: "50.0$", promo: true },
    { amount: "3594 + 224p", price: "200.0$", promo: true },
  ];

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
        {offers.map((offer, index) => (
          <div
            key={index}
            className={`offer-card ${offer.promo ? "promo" : ""}`}
          >
            <p>{offer.amount}</p>
            <img src="path_to_gem_image" alt="Gem" />
            <p>{offer.price}</p>
          </div>
        ))}
      </div>
    </div>
    <Footer /></>
};

export default Beryle;
