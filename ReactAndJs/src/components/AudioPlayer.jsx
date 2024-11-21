import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

// Shared audio player to avoid multiple instances
let sharedAudio = null;

const AudioPlayer = () => {
  const location = useLocation(); // Get the current route
  const [currentTrack, setCurrentTrack] = useState(null);

  useEffect(() => {
    // Routes where audio should play
    const audioRoutes = [
      "/simustar/1", "/simulast", // Add routes that will play the audio
      "/Learn", "/final-self",    // Additional routes for the same audio
    ];

    let newAudioSource = null;

    // Check if current route is one of the defined audio routes
    if (audioRoutes.includes(location.pathname)) {
      newAudioSource = "/audio/GoodEnd.m4a"; // Audio file in public folder
    }

    // If a new audio source is found, update the audio player
    if (newAudioSource && newAudioSource !== currentTrack) {
      // Stop the previous audio if any
      if (sharedAudio) {
        sharedAudio.pause();
        sharedAudio.currentTime = 0; // Reset to start
      }

      // Create a new audio instance and play
      sharedAudio = new Audio(newAudioSource);
      sharedAudio.loop = true; // Loop the audio

      // Debugging: Check if the audio is being created properly
      console.log(`Playing audio from: ${newAudioSource}`);
      
      sharedAudio
        .play()
        .then(() => {
          setCurrentTrack(newAudioSource); // Successfully started playing
          console.log("Audio started playing!");
        })
        .catch((err) => {
          console.error("Error playing audio:", err);
        });
    }

    // Cleanup when component unmounts or route changes
    return () => {
      if (!audioRoutes.includes(location.pathname)) {
        // Stop the audio if no longer on the specified routes
        if (sharedAudio) {
          sharedAudio.pause();
          sharedAudio.currentTime = 0;
          sharedAudio = null;
        }
      }
    };
  }, [location.pathname, currentTrack]); // Re-run effect when the route changes

  return null; // No UI is needed for the audio player
};

export default AudioPlayer;
