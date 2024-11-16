import React from 'react';
import './AdminHome.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

function AdminHome() {
    return <><Navbar />
    <div className="admin-home-container">
      <main className="admin-main-content">
        <div className="section quest-section">
          <h1>เควสต์</h1>
          <p>Quest</p>
        </div>
        <div className="section shop-section">
          <h1>ร้านค้า</h1>
          <p>Shop</p>
        </div>
        <div className="slime-list">
          SLIME LIST
        </div>
        <div className="section contact-section">
          <h1>จดหมายถึงแอดมิน</h1>
          <p>Contact Admin</p>
        </div>
      </main>
    </div>
    <Footer /></>
}

export default AdminHome;
