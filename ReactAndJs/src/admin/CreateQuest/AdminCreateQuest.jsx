import React, { useState } from 'react';
import './AdminCreateQuest.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import { useNavigate } from 'react-router-dom';

const AdminCreateQuest = () => {
  const navigate = useNavigate();
  const [questName, setQuestName] = useState('');
  const [questDescription, setQuestDescription] = useState('');
  const [difficulty, setDifficulty] = useState('');
  const [berylReward, setBerylReward] = useState('');
  const [pointReward, setPointReward] = useState('');
  const [questSubmitMethod, setQuestSubmitMethod] = useState('');
  const [targetObject, setTargetObject] = useState('');
  const [suitableMBTI, setSuitableMBTI] = useState([]);
  const [availableTime, setAvailableTime] = useState([]);

  const targetOptions = [
    'person', 'bicycle', 'car', 'motorcycle', 'airplane', 'bus', 'train',
    'truck', 'boat', 'traffic light', 'fire hydrant', 'stop sign', 'parking meter',
    'bench', 'bird', 'cat', 'dog', 'horse', 'sheep', 'cow', 'elephant',
    'bear', 'zebra', 'giraffe', 'backpack', 'umbrella', 'handbag', 'tie',
    'suitcase', 'frisbee', 'skis', 'snowboard', 'sports ball', 'kite',
    'baseball bat', 'baseball glove', 'skateboard', 'surfboard', 'tennis racket',
    'bottle', 'wine glass', 'cup', 'fork', 'knife', 'spoon', 'bowl', 'banana',
    'apple', 'sandwich', 'orange', 'broccoli', 'carrot', 'hot dog', 'pizza',
    'donut', 'cake', 'chair', 'couch', 'potted plant', 'bed', 'dining table',
    'toilet', 'tv', 'laptop', 'mouse', 'remote', 'keyboard', 'cell phone',
    'microwave', 'oven', 'toaster', 'sink', 'refrigerator', 'book', 'clock',
    'vase', 'scissors', 'teddy bear', 'hair drier', 'toothbrush', 'other'
  ];

  const mbtiOptions = ['INTJ', 'ENTP', 'INFJ', 'ESFP'];
  const timeSlots = ['08:00-12:00', '14:00-18:00', '18:00-22:00'];

  const dropdownStyle = {
    width: '100%',
    padding: '10px',
    border: '1px solid #ccc',
    borderRadius: '5px',
    fontSize: '16px',
    backgroundColor: '#f9f9f9',
    boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
    appearance: 'none',
    marginBottom: '10px',
  };

  const handleCreateQuest = async () => {
    if (
      !questName ||
      !questDescription ||
      !difficulty ||
      !berylReward ||
      !pointReward ||
      !questSubmitMethod ||
      !targetObject ||
      !suitableMBTI.length ||
      !availableTime.length
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
      } else {
        const error = await response.json();
        alert(`Failed to create quest: ${error.message}`);
      }
    } catch (err) {
      alert('An error occurred while creating the quest.');
    }
  };

  return (
    <>
      <Navbar />
      <button className="button2" onClick={() => navigate('/AdminQuestBoard')}>เควสบอร์ด</button>

      <div className="admin-create-quest">
        <div className="quest-detail">
          <h2>Quest Detail</h2>

          <div className="input-container">
            <label>ชื่อเควส</label>
            <input
              type="text"
              placeholder="ชื่อเควส"
              value={questName}
              onChange={(e) => setQuestName(e.target.value)}
            />
          </div>

          <div className="input-container">
            <label>รายละเอียดเควส</label>
            <textarea
              placeholder="รายละเอียดเควส"
              value={questDescription}
              onChange={(e) => setQuestDescription(e.target.value)}
            />
          </div>

          <div className="input-container">
            <label>ระดับความยาก</label>
            <select
              value={difficulty}
              onChange={(e) => setDifficulty(e.target.value)}
              style={dropdownStyle}
            >
              <option value="">เลือกระดับความยาก</option>
              <option value="1">ง่าย</option>
              <option value="2">กลาง</option>
              <option value="3">ยาก</option>
            </select>
          </div>

          <div className="input-container">
            <label>รางวัลเบริล</label>
            <select
              value={berylReward}
              onChange={(e) => setBerylReward(e.target.value)}
              style={dropdownStyle}
            >
              <option value="">เลือกรางวัลเบริล</option>
              {[10, 20, 50, 100].map((reward) => (
                <option key={reward} value={reward}>
                  {reward}
                </option>
              ))}
            </select>
          </div>

          <div className="input-container">
            <label>รางวัลคะแนน</label>
            <select
              value={pointReward}
              onChange={(e) => setPointReward(e.target.value)}
              style={dropdownStyle}
            >
              <option value="">เลือกรางวัลคะแนน</option>
              {[5, 10, 20, 50].map((points) => (
                <option key={points} value={points}>
                  {points}
                </option>
              ))}
            </select>
          </div>

          <div className="input-container">
            <label>วิธีการส่งงาน</label>
            <select
              value={questSubmitMethod}
              onChange={(e) => setQuestSubmitMethod(e.target.value)}
              style={dropdownStyle}
            >
              <option value="">เลือกวิธีส่งงาน</option>
              <option value="image">Image</option>
              <option value="text">Text</option>
            </select>
          </div>

          <div className="input-container">
            <label>เลือกเป้าหมาย</label>
            <select
              value={targetObject}
              onChange={(e) => setTargetObject(e.target.value)}
              style={dropdownStyle}
            >
              <option value="">เลือกเป้าหมาย</option>
              {targetOptions.map((target) => (
                <option key={target} value={target}>
                  {target}
                </option>
              ))}
            </select>
          </div>

          <div className="input-container">
            <label>Suitable MBTI</label>
            <select
              value={suitableMBTI}
              onChange={(e) =>
                setSuitableMBTI(Array.from(e.target.selectedOptions, (opt) => opt.value))
              }
              style={dropdownStyle}
            >
                        <option value="">Select MBTI</option>
                        <option value="INTJ">INTJ</option>
                        <option value="ENTP">ENTP</option>
                        <option value="INTP">INTP</option>
                        <option value="ENTJ">ENTJ</option>
                        <option value="ESTP">ESTP</option>
                        <option value="ESFP">ESFP</option>
                        <option value="ISTP">ISTP</option>
                        <option value="ISFP">ISFP</option>
                        <option value="ESTJ">ESTJ</option>
                        <option value="ISTJ">ISTJ</option>
                        <option value="ISFJ">ISFJ</option>
                        <option value="ESFJ">ESFJ</option>
                    </select>
          </div>

          <div className="input-container">
            <label>Available Time</label>
            <select
              value={availableTime}
              onChange={(e) =>
                setAvailableTime(Array.from(e.target.selectedOptions, (opt) => opt.value))
              }
              style={dropdownStyle}
            >
              <option value="">เลือกเวลา</option>
              {timeSlots.map((slot) => (
                <option key={slot} value={slot}>
                  {slot}
                </option>
              ))}
            </select>
          </div>

          <button onClick={handleCreateQuest}>สร้างเควส</button>
        </div>
      </div>

      <Footer />
    </>
  );
};

export default AdminCreateQuest;
