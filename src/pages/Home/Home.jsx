import React from 'react';
import { Link } from 'react-router-dom'; // Import Link
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import './Home.css';

export default function Home() {
  return <><Navbar />
    
    <div className="home-container">

      
      {/* Main Quest Section */}
      <div className="section quest-section">
        <Link to="/questboard" className="quest-link">
          <h1>เควสต์</h1>
          <p>Quest</p>
          <p className="refresh-timer">รีเฟรชในอีก 5.55.55 ชั่วโมง</p>
        </Link>
      </div>

      {/* Shop and Room Section */}
      <div className="shop-room-container">
        <div className="section shop-section">
        <Link to="/Shop" className="quest-link">
          <h2>ร้านค้า</h2>
          <p>Shop</p>
          </Link>
        </div>
        <div className="section room-section">
        <Link to="/Myroom" className="quest-link">
          <h2>ห้องพักใจ</h2>
          <p>My room</p>
          </Link>
        </div>
      </div>
    </div>
    <Footer /></>
}
