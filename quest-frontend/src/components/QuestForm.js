import React, { useState } from 'react';

const QuestForm = ({ onCreate }) => {
  const [questName, setQuestName] = useState('');
  const [difficulty, setDifficulty] = useState(1);
  const [questDescription, setQuestDescription] = useState('');
  const [questSubmitMethod, setQuestSubmitMethod] = useState('Text');
  const [berylReward, setBerylReward] = useState(0);
  const [pointReward, setPointReward] = useState(0);

  const handleSubmit = (e) => {
    e.preventDefault();

    const newQuest = {
      questName,
      difficulty,
      questDescription,
      questSubmitMethod,
      berylReward,
      pointReward,
    };

    onCreate(newQuest);
  };

  return (
    <div>
      <h2>Create New Quest</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Quest Name</label>
          <input
            type="text"
            value={questName}
            onChange={(e) => setQuestName(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Difficulty</label>
          <input
            type="number"
            value={difficulty}
            onChange={(e) => setDifficulty(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Quest Description</label>
          <textarea
            value={questDescription}
            onChange={(e) => setQuestDescription(e.target.value)}
            required
          ></textarea>
        </div>
        <div>
          <label>Quest Submit Method</label>
          <select
            value={questSubmitMethod}
            onChange={(e) => setQuestSubmitMethod(e.target.value)}
            required
          >
            <option value="Text">Text</option>
            <option value="Image">Image</option>
          </select>
        </div>
        <div>
          <label>Beryl Reward</label>
          <input
            type="number"
            value={berylReward}
            onChange={(e) => setBerylReward(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Point Reward</label>
          <input
            type="number"
            value={pointReward}
            onChange={(e) => setPointReward(e.target.value)}
            required
          />
        </div>
        <button type="submit">Create Quest</button>
      </form>
    </div>
  );
};

export default QuestForm;