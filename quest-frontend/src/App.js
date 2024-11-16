import React from "react";
import CreateQuestForm from "./components/QuestForm";
import QuestList from "./components/QuestList";

const App = () => {
  return (
    <div className="App">
      <h1>Quest Management</h1>
      <CreateQuestForm />
      <QuestList />
    </div>
  );
};

export default App;