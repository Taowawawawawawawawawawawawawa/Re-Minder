import React, { useState } from "react";
import axios from "axios";

const QuestDetails = ({ quest }) => {
    const [loading, setLoading] = useState(false);

    const submitQuest = async () => {
        setLoading(true);

        try {
            await axios.post("http://localhost:8203/questlog/submit", {
                questId: quest.questId,
                questName: quest.questName,
                questDescription: quest.questDescription,
                difficulty: quest.difficulty,
                berylReward: quest.berylReward,
                pointReward: quest.pointReward,
                status: "Pending Review",  // ส่งสถานะ "รอตรวจ"
            });
            alert("Quest submitted successfully!");
        } catch (error) {
            console.error("Error submitting quest:", error);
            alert("Failed to submit quest.");
        }

        setLoading(false);
    };

    return (
        <div>
            <h3>{quest.questName}</h3>
            <p>{quest.questDescription}</p>
            <button onClick={submitQuest} disabled={loading}>
                {loading ? "Submitting..." : "Submit Quest"}
            </button>
        </div>
    );
};

export default QuestDetails;