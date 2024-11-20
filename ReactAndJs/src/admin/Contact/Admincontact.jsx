import React, { useState, useEffect } from 'react';
import './Admincontact.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import Dad from '../../images/Pond_Dad.png'; // Correctly import the image

const Admincontact = () => {
  const [selectedOption, setSelectedOption] = useState('Report bug issue');
  const [showDetail, setShowDetail] = useState(false);

  const handleSelectOption = (option) => {
    setSelectedOption(option);
    setShowDetail(true);
  };

  // Optional: Log state changes for debugging
  useEffect(() => {
    console.log('State updated:', selectedOption, showDetail);
  }, [selectedOption, showDetail]);

  return (
    <>
      <Navbar />
      <div className="admin-contact">
        <div className="contact-section">
          <h2>Contact</h2>
          <select 
            onChange={(e) => {
              setSelectedOption(e.target.value);
              setShowDetail(true); // Show detail when option is selected
            }}
            value={selectedOption}
          >
            <option value="filter" disabled>Filter</option>
            <option value="bug">Report bug issue</option>
            <option value="spam">Report สแปม user</option>
            <option value="ads">ต้องการติดต่อโฆษณา</option>
          </select>
          
          {/* Option buttons (optional, remove if you don't need them) */}
          <button onClick={() => handleSelectOption('Report bug issue')}>Report bug issue</button>
          <button onClick={() => handleSelectOption('Report สแปม user')}>Report สแปม user</button>
          <button onClick={() => handleSelectOption('ต้องการติดต่อโฆษณา')}>ต้องการติดต่อโฆษณา</button>
        </div>

        {/* Conditional Detail Section */}
        {showDetail && (
          <div className="detail-section">
            <h2>Detail</h2>

            {selectedOption === 'Report สแปม user' && (
              <>
                <p>ตรวจพบ user ที่มีลักษณะการส่งข้อความคล้ายสแปม</p>
                <div className="image-gallery">
                  {/* Display images here */}
                  <img src={Dad} alt="spam" />
                  <img src={Dad} alt="spam" />
                </div>
                <p className="source-text">ส่งจาก SuperSlime 011</p>
                <button className="contact-btn">ติดต่อกลับ</button>
              </>
            )}

            {selectedOption === 'Report bug issue' && (
              <p>กรุณารายงานปัญหาที่พบ</p>
            )}

            {selectedOption === 'ต้องการติดต่อโฆษณา' && (
              <p>เรายินดีที่จะร่วมมือกับคุณในการโฆษณา</p>
            )}
          </div>
        )}
      </div>
      <Footer />
    </>
  );
};

export default Admincontact;
