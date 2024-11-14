import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Navbar from '../../components/Navbar/Navbar'; 
import Footer from '../../components/Footer/footer'; 

import QuestBoard from '../Questboard/Questboard'; 
import './Home.css'; 

export default function Home() {
  return (
    <Router>
      <Navbar />
      <div className="home-container">
        <Routes>
          {/* Main Page */}
          <Route
            path="/"
            element={
              <>
                {/* Main Quest Section */}
                <div className="section quest-section">
                  <Link to="/Questboard" className="quest-link">
                    <h1>เควสต์</h1>
                    <p>Quest</p>
                    <p className="refresh-timer">รีเฟรชในอีก 5.55.55 ชั่วโมง</p>
                  </Link>
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
              </>
            }
          />

        </Routes>
      </div>
      <Footer />
    </Router>
  );
}
