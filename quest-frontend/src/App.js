import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import CreateQuestForm from "./components/QuestForm"; // ฟอร์มสำหรับสร้างเควส
import QuestList from "./components/QuestList"; // สำหรับแสดงรายการเควส
import QuestLogList from "./components/QuestLogList"; // สำหรับแสดงรายการ quest log

const App = () => {
  return (
    <Router>
      <div>
        <h1>Quest Management App</h1>
        <nav>
          <ul>
            <li>
              <a href="/">Create Quest</a>
            </li>
            <li>
              <a href="/quests">Quest List</a>
            </li>
            <li>
              <a href="/questlogs">Quest Logs</a>
            </li>
          </ul>
        </nav>
        <Routes>
          <Route path="/" element={<CreateQuestForm />} />
          <Route path="/quests" element={<QuestList />} />
          <Route path="/questlogs" element={<QuestLogList />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;