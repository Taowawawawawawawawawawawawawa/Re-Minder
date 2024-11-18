import React, { useState } from 'react';

function QuestImageForm() {
    const [selectedFile, setSelectedFile] = useState(null);
    const [results, setResults] = useState([]);

    const handleFileChange = (e) => {
        setSelectedFile(e.target.files[0]);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!selectedFile) {
            alert("Please upload a file!");
            return;
        }
        // ทำการ submit file
        try {
            const formData = new FormData();
            formData.append("file", selectedFile);

            const response = await fetch("http://localhost:8000/detect", {
                method: "POST",
                body: formData,
            });

            const data = await response.json();
            setResults(data.predictions);
        } catch (error) {
            console.error("Error submitting image:", error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="file" onChange={handleFileChange} />
            <button type="submit">Submit</button>
            <div>
                {results.map((result, index) => (
                    <p key={index}>{result.name}</p>
                ))}
            </div>
        </form>
    );
}

export default QuestImageForm;