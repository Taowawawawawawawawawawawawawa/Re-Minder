import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer'; 

import QuestBoard from '../Questboard/Questboard'; // Ensure the import is correct
import './Home.css'; 

export default function Home() {
  return (
    <Router>
      <Navbar />
      <div className="home-container">
        <Routes>
          {/* Main Page Route */}
          <Route
            path="/"
            element={
              <>
                {/* Main Quest Section */}
                <div className="section quest-section">
                  <Link to="/questboard" className="quest-link">  {/* Changed '/Questboard' to '/questboard' */}
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

          {/* QuestBoard Page Route */}
          <Route path="/questboard" element={<QuestBoard />} /> {/* Added this route for QuestBoard */}
        </Routes>
      </div>
      <Footer />
    </Router>
  );
}
