import React from 'react';
import { useNavigate } from 'react-router-dom';
import './AdminHome.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

function AdminHome() {
  const navigate = useNavigate();
    return <><Navbar />
    <div className="admin-home-container">
      <main className="admin-main-content">
        <div className="section admin-quest-section" onClick={() => navigate('/AdminCreateQuest')}> 
          <admin-h1>เควสต์</admin-h1>
          <admin-p>Quest</admin-p>
        </div>
        <div className="section shopadmin-section" onClick={() => navigate('/AdminShop')}>
          <admin-h1>เพิ่มของเข้าร้านค้า</admin-h1>
          <admin-p>Add to Shop</admin-p>
        </div>
        <div className="slime-list" onClick={() => navigate('/Userlist')}>
          SLIME LIST
        </div>
        <div className="section admin-contact-section" onClick={() => navigate('/Admincontact')}>
          <admin-h1>จดหมายถึงแอดมิน</admin-h1>
          <admin-p>Contact Admin</admin-p>
        </div>
      </main>
    </div>
    <Footer /></>
}

export default AdminHome;
