import React, { useState, useEffect } from 'react';
import './AdminCreateQuest.css';
import { Link } from 'react-router-dom'; // Import Link
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const AdminCreateQuest = () => {
  const [questName, setQuestName] = useState('');
  const [questDescription, setQuestDescription] = useState('');
  const [difficulty, setDifficulty] = useState('');
  const [berylReward, setBerylReward] = useState('');
  const [pointReward, setPointReward] = useState('');
  const [questSubmitMethod, setQuestSubmitMethod] = useState('');
  const [targetObject, setTargetObject] = useState('');
  const [suitableMBTI, setSuitableMBTI] = useState([]);
  const [availableTime, setAvailableTime] = useState([]);
  const [questList, setQuestList] = useState([]);

  // Fetch quest list on component mount
  // useEffect(() => {
  //   fetchQuestList();
  // }, []);

  // const fetchQuestList = async () => {
  //   try {
  //     const response = await fetch('http://localhost:8202/quests/create');
  //     if (response.ok) {
  //       const quests = await response.json();
  //       setQuestList(quests);
  //     } else {
  //       alert('Failed to fetch quests.');
  //     }
  //   } catch (err) {
  //     alert('An error occurred while fetching quests.');
  //   }
  // };

  const handleCreateQuest = async () => {
    if (
      !questName ||
      !questDescription ||
      !difficulty ||
      !berylReward ||
      !pointReward ||
      !questSubmitMethod ||
      !targetObject ||
      suitableMBTI.length === 0 ||
      availableTime.length === 0
    ) {
      alert('กรุณากรอกข้อมูลให้ครบถ้วน');
      return;
    }

    const questData = {
      questName,
      questDescription,
      difficulty: parseInt(difficulty),
      berylReward: parseInt(berylReward),
      pointReward: parseInt(pointReward),
      questSubmitMethod,
      targetObject,
      suitableMBTI,
      availableTime,
    };

    try {
      const response = await fetch('http://localhost:8202/quests/create', { 
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(questData),
      });

      if (response.ok) {
        alert(`Quest "${questName}" created successfully!`);
        setQuestName('');
        setQuestDescription('');
        setDifficulty('');
        setBerylReward('');
        setPointReward('');
        setQuestSubmitMethod('');
        setTargetObject('');
        setSuitableMBTI([]);
        setAvailableTime([]);
        // fetchQuestList(); // Refresh the quest list
      } else {
        const error = await response.json();
        alert(`Failed to create quest: ${error.message}`);
      }
    } catch (err) {
      alert('An error occurred while creating the quest.');
    }
  };

  const handleAddMBTI = (mbti) => {
    if (!suitableMBTI.includes(mbti)) {
      setSuitableMBTI([...suitableMBTI, mbti]);
    }
  };

  const handleRemoveMBTI = (mbti) => {
    setSuitableMBTI(suitableMBTI.filter((item) => item !== mbti));
  };

  const handleAddTimeSlot = (timeSlot) => {
    if (!availableTime.includes(timeSlot)) {
      setAvailableTime([...availableTime, timeSlot]);
    }
  };

  const handleRemoveTimeSlot = (timeSlot) => {
    setAvailableTime(availableTime.filter((item) => item !== timeSlot));
  };

  return (
    <>
      <Navbar />
      <div className="admin-create-quest">
        {/* Quest Detail Section */}
        <div className="quest-detail">
          <h2>Quest Detail</h2>
          <input
            type="text"
            placeholder="ชื่อเควส"
            value={questName}
            onChange={(e) => setQuestName(e.target.value)}
          />
          <textarea
            placeholder="รายละเอียดเควส"
            value={questDescription}
            onChange={(e) => setQuestDescription(e.target.value)}
          />
          <select
            value={difficulty}
            onChange={(e) => setDifficulty(e.target.value)}
          >
            <option value="">ระดับความยาก</option>
            <option value="1">ง่าย</option>
            <option value="2">กลาง</option>
            <option value="3">ยาก</option>
          </select>
          <input
            type="number"
            placeholder="รางวัลเบริล"
            value={berylReward}
            onChange={(e) => setBerylReward(e.target.value)}
          />
          <input
            type="number"
            placeholder="รางวัลคะแนน"
            value={pointReward}
            onChange={(e) => setPointReward(e.target.value)}
          />
          <input
            type="text"
            placeholder="วิธีส่งงาน (เช่น upload)"
            value={questSubmitMethod}
            onChange={(e) => setQuestSubmitMethod(e.target.value)}
          />
          <input
            type="text"
            placeholder="เป้าหมาย (เช่น dog)"
            value={targetObject}
            onChange={(e) => setTargetObject(e.target.value)}
          />
          <div>
            <h4>Suitable MBTI</h4>
            {['INTJ', 'ENTP', 'INFJ', 'ESFP'].map((mbti) => (
              <button
                key={mbti}
                onClick={() =>
                  suitableMBTI.includes(mbti)
                    ? handleRemoveMBTI(mbti)
                    : handleAddMBTI(mbti)
                }
              >
                {mbti} {suitableMBTI.includes(mbti) ? '✓' : ''}
              </button>
            ))}
          </div>
          <div>
            <h4>Available Time</h4>
            {['08:00-12:00', '14:00-18:00', '18:00-22:00'].map((slot) => (
              <button
                key={slot}
                onClick={() =>
                  availableTime.includes(slot)
                    ? handleRemoveTimeSlot(slot)
                    : handleAddTimeSlot(slot)
                }
              >
                {slot} {availableTime.includes(slot) ? '✓' : ''}
              </button>
            ))}
          </div>
          <button onClick={handleCreateQuest}>สร้างเควส</button>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default AdminCreateQuest;