import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

let sharedAudio = null; // Stores the shared audio instance

const AudioPlayer = () => {
  const location = useLocation(); // Track the current URL path
  const [currentTrack, setCurrentTrack] = useState(null); // Keep track of the currently playing audio

  useEffect(() => {
    const group1 = ["/simustar/1", "/simulast"]; // Group 1 paths
    const group2 = ["/Learn", "/final-self"]; // Group 2 paths

    let newAudioSource = null;

    // Define audio sources for different paths
    if (group1.includes(location.pathname)) {
      newAudioSource = "/audio/GoodEnd.mp3"; // Path for GoodEnd.mp3 in the public folder
    } else if (group2.includes(location.pathname)) {
      newAudioSource = "/audio/GoodEnd.m4a"; // Path for GoodEnd.m4a in the public folder
    }

    // Check if the audio source has changed and update accordingly
    if (newAudioSource && newAudioSource !== currentTrack) {
      // Pause and reset the previous audio if it exists
      if (sharedAudio) {
        sharedAudio.pause();
        sharedAudio.currentTime = 0;
      }

      // Create a new audio instance and start playing
      sharedAudio = new Audio(newAudioSource);
      sharedAudio.loop = true; // Loop the audio
      sharedAudio
        .play()
        .then(() => setCurrentTrack(newAudioSource)) // Update the current track once it's playing
        .catch((err) => console.error("Error playing audio:", err));
    }

    // Cleanup function to stop audio when the component is unmounted or if the path changes
    return () => {
      if (!group1.includes(location.pathname) && !group2.includes(location.pathname)) {
        // Stop audio if leaving the groups
        if (sharedAudio) {
          sharedAudio.pause();
          sharedAudio.currentTime = 0;
          sharedAudio = null;
        }
      }
    };
  }, [location.pathname, currentTrack]); // Update when location or currentTrack changes

  return null; // No need to render anything for this component
};

export default AudioPlayer;
