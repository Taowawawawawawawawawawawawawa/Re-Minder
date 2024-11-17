import React from "react";
import "./AdminQuestBoard.css";
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const AdminQuestBoard = () => {

  const [questsAM,setQuestAM] = useState([]);

  useEffect(()=>{
    const fetchquestAM = async() => {
      const dataAM = await fetch("http://API/");
      const json = await dataAM.json();
      setQuestAM(json);
    };
    fetchquestAM();



  },[]);




    return <><Navbar />
    <div className="admin-quest-board">
      <div className="quest-container">
        <div className="quest-list">
          <h2>Quest List</h2>
          <div className="difficulty-tabs">
            <button className="tab active">ง่าย</button>
            <button className="tab">กลาง</button>
            <button className="tab">ยาก</button>
          </div>
          <ul className="quest-items">
            <li className="quest-item">
              ขอกำลังใจหน่อย
              <span className="quest-status">รอตรวจ</span>
            </li>
            <li className="quest-item">
              ถ่ายรูปท้องฟ้า...
              <span className="quest-status">รอตรวจ</span>
            </li>
            <li className="quest-item">
              หิวมั้ย?
              <span className="quest-status">รอตรวจ</span>
            </li>
            {/* Additional quest items */}
          </ul>
        </div>

        <div className="quest-detail">
          <h2>Detail</h2>
          <p className="quest-title">ถ่ายรูปท้องฟ้าตอนเย็นให้ดูหน่อยสิ</p>
          <div className="quest-info">
            <p>รายละเอียดเควส: ...................</p>
            <p>วิธีส่งงาน: JPG/PNG</p>
            <p>ระดับความยาก: ง่าย</p>
          </div>
          <div className="approval-section">
            <button className="approve-button">อนุมัติ III</button>
            <button className="reject-button">ไม่อนุมัติ III</button>
          </div>
          <div className="action-buttons">
            <button className="cancel-quest">ระงับเควส</button>
            <button className="approve-quest">อนุมัติ</button>
          </div>
          <input type="text" className="reason-input" placeholder="เหตุผล" />
        </div>
      </div>
    </div>
    <Footer /></>
};

export default AdminQuestBoard;
