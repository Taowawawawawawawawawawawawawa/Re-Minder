import React, { useEffect, useState } from "react";
import axios from "axios";

function QuestLogList() {
    const [questLogs, setQuestLogs] = useState([]);

    useEffect(() => {
        fetchQuestLogs();
    }, []);

    const fetchQuestLogs = async () => {
        try {
            const response = await axios.get("http://localhost:8203/api/questlog/all");
            setQuestLogs(response.data);
        } catch (error) {
            console.error("Error fetching quest logs:", error);
        }
    };

    return (
        <div>
            <h1>Quest Log List</h1>
            <table>
                <thead>
                    <tr>
                        <th>Quest Name</th>
                        <th>User ID</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {questLogs.map((log) => (
                        <tr key={log.id}>
                            <td>{log.questId}</td>
                            <td>{log.userId}</td>
                            <td>{log.status}</td>
                            <td>
                                <button onClick={() => approveQuestLog(log.id)}>Approve</button>
                                <button onClick={() => rejectQuestLog(log.id)}>Reject</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default QuestLogList;