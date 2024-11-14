import React from "react";
import { ClipLoader } from "react-spinners";

function Loading() {
  return (
    <div>
      <div className="w-full h-screen flex justify-center items-center">
        <ClipLoader color="#2A6354" size={160} />
      </div>
    </div>
  );
}

export default Loading;
