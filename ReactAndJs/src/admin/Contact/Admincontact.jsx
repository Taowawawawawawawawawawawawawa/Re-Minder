import React, { useState, useEffect } from 'react';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import { useNavigate } from 'react-router-dom';
import Dad from '../../images/Pond_Dad.png'; // Correctly import the image
import BG from '../../images/quest_bg.jpg'; // Import background image
import BG2 from '../../images/BGwood.png'; // Import background image

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

  // Inline Styles
  const pageStyle = {
    background: `url(${BG}), #D9D9D9`, // Correct way to include the background image
    width: '100%',
    minHeight: '100vh',
    fontFamily: 'Arial, sans-serif',
  };

  const adminContactStyle = {
    display: 'flex',
    gap: '40px',
    flexWrap: 'wrap',
    justifyContent: 'center',
    marginTop: '40px',
    position: 'relative',
    zIndex: 100,
    pointerEvents: 'all',
    padding: '0 20px', // Added padding to prevent content from touching the edges
  };

  const sectionStyle = {
    background : `url(${BG2}), #D9D9D9`,
    border: '2px solid #8b5a2b',
    borderRadius: '12px',
    padding: '30px', // Increased padding for a larger container
    boxShadow: '3px 3px 20px rgba(0, 0, 0, 0.3)', // Enhanced shadow for a more prominent effect
    width: '350px', // Increased width for a larger section
    maxWidth: '100%', // Ensure it is responsive
    transition: 'transform 0.3s ease, box-shadow 0.3s ease',
    position: 'relative',
    zIndex: 1,
    pointerEvents: 'all',
    marginBottom: '30px', // Added space between sections
  };

  const buttonStyle = {
    width: '100%',
    marginBottom: '15px', // Increased bottom margin
    padding: '12px', // Larger padding for buttons
    border: '1px solid #c69a6b',
    borderRadius: '5px',
    fontSize: '1.1em', // Slightly larger font size for buttons
    backgroundColor: '#fff',
    color: '#5d3b1a',
    transition: 'border-color 0.3s ease',
    boxSizing: 'border-box',
    cursor: 'pointer',
  };

  const contactButtonStyle = {
    backgroundColor: '#8b5a2b',
    color: '#fff',
    cursor: 'pointer',
    transition: 'background-color 0.3s ease, transform 0.2s ease',
    padding: '12px', // Increased padding for consistency
    fontSize: '1.1em', // Increased font size
  };

  const contactButtonHoverStyle = {
    backgroundColor: '#a5673f',
    transform: 'translateY(-2px)',
  };

  const imageGalleryStyle = {
    display: 'flex',
    flexWrap: 'wrap',
    gap: '20px', // Increased gap between images
    justifyContent: 'center',
  };

  const imageStyle = {
    width: '80px', // Increased image size for better visibility
    height: '80px',
    borderRadius: '8px',
    border: '2px solid #c69a6b',
    objectFit: 'cover',
    transition: 'transform 0.3s ease',
    cursor: 'pointer',
  };

  const imageHoverStyle = {
    transform: 'scale(1.1)',
  };

  // Inline styles for h2
  const h2Style = {
    fontSize: '2.5em', // Increased font size to make it large
    color: '#ffffff', // Set the text color to white
    fontWeight: 'bold', // Optional: Make the font bold for emphasis
    marginBottom: '20px', // Add space below the heading
  };

  // Inline styles for p
  const pStyle = {
    color: '#ffffff', // Set the text color to white
    fontSize: '1.1em', // Optional: Adjust the font size for readability
    marginBottom: '20px', // Add some space between paragraphs
  };

  return (
    <div style={pageStyle}>
      <Navbar />
      <div style={adminContactStyle}>
        <div style={sectionStyle}>
          <h2 style={h2Style}>Contact</h2>
          <select
            onChange={(e) => {
              setSelectedOption(e.target.value);
              setShowDetail(true); // Show detail when option is selected
            }}
            value={selectedOption}
            style={buttonStyle}
          >
            <option value="filter" disabled>Filter</option>
            <option value="bug">Report bug issue</option>
            <option value="spam">Report สแปม user</option>
            <option value="ads">ต้องการติดต่อโฆษณา</option>
          </select>
          <button
            onClick={() => handleSelectOption('Report bug issue')}
            style={buttonStyle}
          >
            Report bug issue
          </button>
          <button
            onClick={() => handleSelectOption('Report สแปม user')}
            style={buttonStyle}
          >
            Report สแปม user
          </button>
          <button
            onClick={() => handleSelectOption('ต้องการติดต่อโฆษณา')}
            style={buttonStyle}
          >
            ต้องการติดต่อโฆษณา
          </button>
        </div>

        {/* Conditional Detail Section */}
        {showDetail && (
          <div style={sectionStyle}>
            <h2 style={h2Style}>Detail</h2>
            {selectedOption === 'Report สแปม user' && (
              <>
                <p style={pStyle}>ตรวจพบ user ที่มีลักษณะการส่งข้อความคล้ายสแปม</p>
                <div style={imageGalleryStyle}>
                  <img src={Dad} alt="spam" style={imageStyle} />
                  <img src={Dad} alt="spam" style={imageStyle} />
                </div>
                <p className="source-text" style={pStyle}>ส่งจาก SuperSlime 011</p>
                <button
                  style={{ ...contactButtonStyle, ...contactButtonHoverStyle }}
                  className="contact-btn"
                >
                  ติดต่อกลับ
                </button>
              </>
            )}

            {selectedOption === 'Report bug issue' && (
              <p style={pStyle}>กรุณารายงานปัญหาที่พบ</p>
            )}

            {selectedOption === 'ต้องการติดต่อโฆษณา' && (
              <p style={pStyle}>เรายินดีที่จะร่วมมือกับคุณในการโฆษณา</p>
            )}
          </div>
        )}
      </div>
      <Footer />
    </div>
  );
};

export default Admincontact;
