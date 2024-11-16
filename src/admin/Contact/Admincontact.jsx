import React, { useState } from 'react';
import './Admincontact.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const Admincontact = () => {
  const [selectedOption, setSelectedOption] = useState('Report bug issue');
  const [showDetail, setShowDetail] = useState(false);

  const handleSelectOption = (option) => {
    setSelectedOption(option);
    setShowDetail(true);
  };

  return <><Navbar />
    <div className="admin-contact">
      <div className="contact-section">
        <h2>Contact</h2>
        <select onChange={(e) => setSelectedOption(e.target.value)}>
          <option value="filter">filter</option>
          <option value="bug">Report bug issue</option>
          <option value="spam">Report สแปม user</option>
          <option value="ads">ต้องการติดต่อโฆษณา</option>
        </select>
        <button onClick={() => handleSelectOption('Report bug issue')}>Report bug issue</button>
        <button onClick={() => handleSelectOption('Report สแปม user')}>Report สแปม user</button>
        <button onClick={() => handleSelectOption('ต้องการติดต่อโฆษณา')}>ต้องการติดต่อโฆษณา</button>
      </div>

      {showDetail && (
        <div className="detail-section">
          <h2>Detail</h2>
          {selectedOption === 'Report สแปม user' && (
            <>
              <p>ตรวจพบ user ที่มีลักษณะการส่งข้อความคล้ายสแปม</p>
              <div className="image-gallery">
                {/* Placeholder images */}
                <img src="https://via.placeholder.com/50" alt="spam" />
                <img src="https://via.placeholder.com/50" alt="spam" />
                <img src="https://via.placeholder.com/50" alt="spam" />
                <img src="https://via.placeholder.com/50" alt="spam" />
                <img src="https://via.placeholder.com/50" alt="spam" />
                <img src="https://via.placeholder.com/50" alt="spam" />
              </div>
              <p className="source-text">ส่งจาก SuperSlime 011</p>
              <button className="contact-btn">ติดต่อกลับ</button>
            </>
          )}

        </div>
      )}
    </div>
  <Footer /></>
};

export default Admincontact;
