import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import './Home.css';

export default function Home() {
  const navigate = useNavigate(); // Initialize navigate function

  const [timeRemaining, setTimeRemaining] = useState('05:55:55'); // Initial countdown

  // Function to update the countdown timer
  useEffect(() => {
    const countdownInterval = setInterval(() => {
      let [hours, minutes, seconds] = timeRemaining.split(':').map(Number);

      if (seconds > 0) {
        seconds--;
      } else {
        if (minutes > 0) {
          minutes--;
          seconds = 59;
        } else {
          if (hours > 0) {
            hours--;
            minutes = 59;
            seconds = 59;
          } else {
            clearInterval(countdownInterval);
          }
        }
      }

      const newTime = `${String(hours).padStart(2, '0')}:${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
      setTimeRemaining(newTime);

    }, 1000); // Update every second

    return () => clearInterval(countdownInterval);
  }, [timeRemaining]);

  return (
    <>
      <Navbar />
      
      <div className="home-container">
        {/* Main Quest Section */}
        <div className="section quest-section" onClick={() => navigate('/questboard')}>
          <div className="section-content">
            <h1>เควสต์</h1>
            <p className="sub-title">Quest</p>
            <div className="refresh-timer"> รีเฟรชในอีก {timeRemaining}</div>
          </div>
        </div>

        {/* Shop and Room Section */}
        <div className="shop-room-container">
          <div className="section shop-section" onClick={() => navigate('/shop')}>
            <div className="section-content">
              <h2>ร้านค้า</h2>
              <p1 className="sub-title">Shop</p1>
            </div>
          </div>
          <div className="section room-section" onClick={() => navigate('/myroom')}>
            <div className="section-content">
              <h2>ห้องพักใจ</h2>
              <p1 className="sub-title">My room</p1>
            </div>
          </div>
        </div>
      </div>
      
      <Footer />
    </>
  );
}
