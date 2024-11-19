import React from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import './Home.css';

export default function Home() {
  const navigate = useNavigate(); // Initialize navigate function

  return (
    <>
      <Navbar />
      
      <div className="home-container">
        {/* Main Quest Section */}
        <div className="section quest-section" onClick={() => navigate('/questboard')}>
          <div className="section-content">
            <h1>เควสต์</h1>
            <p className="sub-title">Quest</p>
            <p className="refresh-timer">รีเฟรชในอีก 5.55.55 ชั่วโมง</p>
          </div>
        </div>

        {/* Shop and Room Section */}
        <div className="shop-room-container">
          <div className="section shop-section" onClick={() => navigate('/shop')}>
            <div className="section-content">
              <h2>ร้านค้า</h2>
              <p className="sub-title">Shop</p>
            </div>
          </div>
          <div className="section room-section" onClick={() => navigate('/myroom')}>
            <div className="section-content">
              <h2>ห้องพักใจ</h2>
              <p className="sub-title">My room</p>
            </div>
          </div>
        </div>
      </div>
      
      <Footer />
    </>
  );
}
