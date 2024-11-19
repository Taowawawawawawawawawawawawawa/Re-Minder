import React from 'react';
import { useNavigate } from 'react-router-dom';
import './AdminHome.css';
import { Link } from 'react-router-dom'; // Import Link
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

function AdminHome() {
  const navigate = useNavigate();
    return <><Navbar />
    <div className="admin-home-container">
      <main className="admin-main-content">
        <div className="section quest-section" onClick={() => navigate('/AdminCreateQuest')}> 
          <h1>เควสต์</h1>
          <p>Quest</p>
        </div>
        <div className="section shop-section" onClick={() => navigate('/AdminShop')}>
          <h1>เพิ่มของเข้าร้านค้า</h1>
          <p>Add to Shop</p>
        </div>
        <div className="slime-list" onClick={() => navigate('/Userlist')}>
          SLIME LIST
        </div>
        <div className="section contact-section" onClick={() => navigate('/Admincontact')}>
          <h1>จดหมายถึงแอดมิน</h1>
          <p>Contact Admin</p>
        </div>
      </main>
    </div>
    <Footer /></>
}

export default AdminHome;
