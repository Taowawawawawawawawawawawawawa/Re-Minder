import React from 'react';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';
import './Questboard.css';

function QuestBoard() {
  return (
    <div className="quest-board">


      <div className="quest-board-container">
        <div className="quest-content">
          <div className="quest-list">
            <h3>Quest List</h3>
            <div className="difficulty-tabs">
              <button className="tab-button active">ง่าย</button>
              <button className="tab-button">กลาง</button>
              <button className="tab-button">ยาก</button>
            </div>
            <ul className="quest-items">
              <li className="quest-item">
                <p>อรุณสวัสดิ!</p>
                <div className="reward">💎 x2</div>
              </li>
              <li className="quest-item">
                <p>อากาศในวันนี้</p>
                <div className="reward">💎 x2</div>
              </li>
              <li className="quest-item">
                <p>กินอะไรหรือยัง?</p>
                <div className="reward">💎 x2</div>
              </li>
              <li className="quest-item">
                <p>ขอกำลังใจหน่อย!</p>
                <div className="status">สำเร็จ!</div>
              </li>
            </ul>
          </div>

          <div className="quest-detail">
            <h3>Detail</h3>
            <p className="quest-message">
              นี่ๆๆ คุณตื่นหรือยัง? ถ้าตื่นแล้วก็มาทักทายกันหน่อยสิ!
            </p>
            <input type="text" placeholder="ทักทายกันหน่อยสิ..." className="response-input" />
            <div className="action-buttons">
              <button className="send-button">จัดส่ง</button>
              <button className="cancel-button">ยกเลิก</button>
            </div>
          </div>
        </div>

      </div>

    </div>
  );
}

export default QuestBoard;
