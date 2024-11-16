import React from "react";
import "./Point.css";
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const Point = () => {
  const exchanges = new Array(15).fill({
    amount: "300pt",
    icon: "path_to_red_ticket_image",
    reward: "path_to_green_gem_image",
  });

  return <><Navbar />
    <div className="point-exchange">
      <div className="header">
        <h2>แลกพ้อยได้ที่นี่เลย</h2>
      </div>
      <div className="exchange-grid">
        {exchanges.map((exchange, index) => (
          <div key={index} className="exchange-card">
            <img
              className="exchange-icon"
              src={exchange.icon}
              alt="Exchange Item"
            />
            <div className="exchange-info">
              <p className="points">{exchange.amount}</p>
              <img
                className="reward-icon"
                src={exchange.reward}
                alt="Reward Gem"
              />
            </div>
          </div>
        ))}
      </div>
    </div>
    <Footer /></>
};

export default Point;
