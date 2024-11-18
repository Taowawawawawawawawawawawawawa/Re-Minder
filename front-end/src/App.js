import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import QuestImageForm from './QuestImageForm';

function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<QuestImageForm />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;