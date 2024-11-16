import React, { useState } from 'react';
import './Myroom.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer'; 
import { useNavigate } from 'react-router-dom';

const Diary = () => {
  const [entries, setEntries] = useState([
    { date: '25/12/67', content: 'ถึงไดอารี่ของผม: วันนี้...' },
    { date: '25/12/67', content: 'สำรวจเมืองและเก็บเกี่ยว...' },
    { date: '25/12/67', content: 'บันทึกที่ 3...' },
  ]);
  const [currentEntry, setCurrentEntry] = useState({
    date: '30/2/24',
    content: 'ถึงไดอารี่ของผม: วันนี้...',
  });

  const navigate = useNavigate();

  const handleBackToRoom = () => {
    navigate('/Myroom');
  };

  const handleSaveEntry = () => {
    setEntries([...entries, currentEntry]);
  };

  return (
    <>
      <Navbar />
      <div className="game-screen">
        <div className="diary">
          <h2>My Diary</h2>
          <div className="diary-sidebar">
            {entries.map((entry, index) => (
              <button
                key={index}
                className="diary-entry-button"
                onClick={() => setCurrentEntry(entry)}
              >
                {entry.date}
              </button>
            ))}
          </div>

          <div className="diary-main">
            <h3>{currentEntry.date}</h3>
            <textarea
              value={currentEntry.content}
              onChange={(e) =>
                setCurrentEntry({ ...currentEntry, content: e.target.value })
              }
            />
            <button className="upload-button">เพิ่มรูปภาพ</button>
            <div className="controls">
              <button className="control-button" onClick={handleSaveEntry}>
                บันทึก
              </button>
              <button className="control-button" onClick={handleBackToRoom}>
                กลับห้อง
              </button>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default Diary;
