import React from 'react';
import './App.css';
import Navbar from "./components/Navbar/Navbar";
import Footer from './components/Footer/footer';

export default function App(){
  return <><Navbar />
<div className="app-container">
      {/* Main Quest Section */}
      <div className="section quest-section">
        <h1>เควสต์</h1>
        <p>Quest</p>
        <p className="refresh-timer">รีเฟรชในอีก 5.55.55 ชั่วโมง</p>
      </div>

      {/* Shop and Room Section */}
      <div className="shop-room-container">
        <div className="section shop-section">
          <h2>ร้านค้า</h2>
          <p>Shop</p>
        </div>

        <div className="section room-section">
          <h2>ห้องพักใจ</h2>
          <p>My room</p>
        </div>
      </div>
    </div>
  <Footer /></>
}
