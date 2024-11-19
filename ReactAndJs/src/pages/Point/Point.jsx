
import React, { useState, useEffect } from 'react';
import "./Point.css";
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const Point = () => {
  const exchanges = new Array(15).fill({
    amount: "300pt",
    icon: "path_to_red_ticket_image",
    reward: "path_to_green_gem_image",
  });

  const [point, setpoint] = useState([]);
  const [error, setError] = useState(null);

  const fetchpoint = async () => {
    try {
        const response = await fetch("http://localhost:8206/beryl/all");
        if (!response.ok) {
            throw new Error(`API error: ${response.status}`);
        }
        const data = await response.json();
        setpoint(data);
    } catch (err) {
        setError(err.message);
    }
};
useEffect(()=>{
  fetchpoint();
})

  return <><Navbar />
    <div className="point-exchange">
      <div className="header">
        <h2>แลกพ้อยได้ที่นี่เลย</h2>
      </div>
      <div className="exchange-grid">
        {point.map((point) => (
          <div key={point.beryleID} className="exchange-card">
            <img
              className="exchange-icon"
              src={exchange.icon}
              alt="Exchange Item"
            />
            <div className="exchange-info">
              <p className="points">{point.pointAmont}</p>
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
