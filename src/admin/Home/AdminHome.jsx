import React from 'react';
import './AdminHome.css';
import { Link } from 'react-router-dom'; // Import Link
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

function AdminHome() {
    return <><Navbar />
    <div className="admin-home-container">
      <main className="admin-main-content">
        <div className="section quest-section">
        <Link to="/AdminCreateQuest" className="quest-link">
          <h1>เควสต์</h1>
          <p>Quest</p>
          </Link>
        </div>
        <div className="section shop-section">
        <Link to="/AdminShop" className="quest-link">
          <h1>เพิ่มของเข้าร้านค้า</h1>
          <p>Add to Shop</p>
          </Link>
        </div>
        <div className="slime-list">
        <Link to="/Userlist" className="quest-link">
          SLIME LIST
          </Link>
        </div>
        <div className="section contact-section">
        <Link to="/Admincontact" className="quest-link">
          <h1>จดหมายถึงแอดมิน</h1>
          <p>Contact Admin</p>
          </Link>
        </div>
      </main>
    </div>
    <Footer /></>
}

export default AdminHome;
