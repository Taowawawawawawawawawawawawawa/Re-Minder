import React from 'react';
import './Footer.css';
import { useNavigate } from 'react-router-dom';

function Footer() {
  const navigate = useNavigate();

  // Function to handle profile icon click
  const handleAdminClick = () => {
    navigate("/AdminHome "); // Navigate to home
  };
  return (
    <div className="footer">
      <button className="contact-button" onClick={handleAdminClick} >Bypass Admin</button>
    </div>
  );
};


export default Footer;
