import React, { useState } from "react";
import { axiosInstance8202 } from "../api/quests"; // นำเข้า axiosInstance8202

const CreateQuestForm = () => {
  const [questName, setQuestName] = useState("");
  const [questDescription, setQuestDescription] = useState("");
  const [difficulty, setDifficulty] = useState(1);
  const [berylReward, setBerylReward] = useState(0);
  const [pointReward, setPointReward] = useState(0);
  const [questSubmitMethod, setQuestSubmitMethod] = useState("");
  const [availableTime, setAvailableTime] = useState(["09:00", "12:00"]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newQuest = {
      questName,
      questDescription,
      difficulty,
      berylReward,
      pointReward,
      questSubmitMethod,
      availableTime,
    };

    try {
      const response = await axiosInstance8202.post("/quests/create", newQuest); // ใช้ axiosInstance8202 สำหรับ service quest
      console.log(response.data);
    } catch (error) {
      console.error("There was an error creating the quest!", error);
    }
  };


  return (
    <div>
      <h2>Create a New Quest</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Quest Name:</label>
          <input
            type="text"
            value={questName}
            onChange={(e) => setQuestName(e.target.value)}
            required
          />
        </div>

        <div>
          <label>Quest Description:</label>
          <textarea
            value={questDescription}
            onChange={(e) => setQuestDescription(e.target.value)}
            required
          />
        </div>

        <div>
          <label>Difficulty:</label>
          <input
            type="number"
            value={difficulty}
            onChange={(e) => setDifficulty(Number(e.target.value))}
            min="1"
            max="5"
            required
          />
        </div>

        <div>
          <label>Beryl Reward:</label>
          <input
            type="number"
            value={berylReward}
            onChange={(e) => setBerylReward(Number(e.target.value))}
            required
          />
        </div>

        <div>
          <label>Point Reward:</label>
          <input
            type="number"
            value={pointReward}
            onChange={(e) => setPointReward(Number(e.target.value))}
            required
          />
        </div>

        <div>
          <label>Quest Submit Method:</label>
          <input
            type="text"
            value={questSubmitMethod}
            onChange={(e) => setQuestSubmitMethod(e.target.value)}
            required
          />
        </div>

        <div>
          <label>Available Time (Comma separated, e.g., "09:00, 12:00"):</label>
          <input
            type="text"
            value={availableTime.join(", ")}
            onChange={(e) => setAvailableTime(e.target.value.split(", ").map(time => time.trim()))}
            required
          />
        </div>

        <button type="submit">Create Quest</button>
      </form>
    </div>
  );
};

export default CreateQuestForm;