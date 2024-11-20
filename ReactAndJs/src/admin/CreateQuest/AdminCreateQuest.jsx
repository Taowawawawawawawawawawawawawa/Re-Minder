import React, { useState } from 'react';
import './AdminCreateQuest.css';
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
  const [searchTerm, setSearchTerm] = useState('');
  const [suitableMBTI, setSuitableMBTI] = useState([]);  // เปลี่ยนให้เป็น Array
  const [availableTime, setAvailableTime] = useState('');

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

  const filteredTargets = targetOptions.filter((target) =>
    target.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleCreateQuest = async () => {
    if (
      !questName ||
      !questDescription ||
      !difficulty ||
      !berylReward ||
      !pointReward ||
      !questSubmitMethod ||
      !targetObject ||
      suitableMBTI.length === 0 ||  // ตรวจสอบว่าได้เลือก MBTI อย่างน้อยหนึ่งค่า
      !availableTime
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
      suitableMBTI,  // ส่งข้อมูลเป็น Array
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
        // Reset form fields
        setQuestName('');
        setQuestDescription('');
        setDifficulty('');
        setBerylReward('');
        setPointReward('');
        setQuestSubmitMethod('');
        setTargetObject('');
        setSearchTerm('');
        setSuitableMBTI([]);  // Reset suitableMBTI เป็น Array เปล่า
        setAvailableTime('');
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
      <div className="admin-create-quest">
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
          <select
            value={questSubmitMethod}
            onChange={(e) => setQuestSubmitMethod(e.target.value)}
          >
            <option value="">เลือกวิธีส่งงาน</option>
            <option value="image">Image</option>
            <option value="text">Text</option>
          </select>
          <div className="searchable-dropdown">
            <input
              type="text"
              placeholder="ค้นหาเป้าหมาย..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
            <select
              value={targetObject}
              onChange={(e) => setTargetObject(e.target.value)}
            >
              <option value="">เลือกเป้าหมาย</option>
              {filteredTargets.map((target) => (
                <option key={target} value={target}>
                  {target}
                </option>
              ))}
            </select>
          </div>
          <select
            multiple  // เพิ่ม attribute multiple เพื่อให้เลือกได้หลายค่า
            value={suitableMBTI}
            onChange={(e) => {
              const selectedValues = Array.from(e.target.selectedOptions, option => option.value);
              setSuitableMBTI(selectedValues);  // อัพเดตค่าของ suitableMBTI เป็น Array
            }}
          >
            <option value="">Suitable MBTI</option>
            {mbtiOptions.map((mbti) => (
              <option key={mbti} value={mbti}>
                {mbti}
              </option>
            ))}
          </select>
          <select
            value={availableTime}
            onChange={(e) => setAvailableTime(e.target.value)}
          >
            <option value="">Available Time</option>
            {timeSlots.map((slot) => (
              <option key={slot} value={slot}>
                {slot}
              </option>
            ))}
          </select>
          <button onClick={handleCreateQuest}>สร้างเควส</button>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default AdminCreateQuest;