from fastapi import FastAPI, File, UploadFile
from fastapi.responses import JSONResponse
from fastapi.middleware.cors import CORSMiddleware
import io
from PIL import Image
import torch

app = FastAPI()

# ตั้งค่า CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:3000"],  # ตั้งค่า React URL
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# โหลด YOLOv5 Model เพียงครั้งเดียวตอนเริ่มต้น
print("Loading YOLOv5 model...")
model = torch.hub.load('ultralytics/yolov5', 'yolov5s')
print("YOLOv5 model loaded successfully.")

# วัตถุที่ต้องการตรวจจับ (สามารถปรับตามที่ต้องการ)
target_objects = ["dog", "cat"]  # ตัวอย่างการกรองแค่ "dog" และ "cat"

@app.post("/detect")
async def detect_object(file: UploadFile = File(...)):
    try:
        # อ่านไฟล์ภาพ
        print("Reading uploaded file...")
        image_data = await file.read()
        image = Image.open(io.BytesIO(image_data))
        print("Image loaded successfully.")

        # ตรวจจับวัตถุด้วย YOLOv5
        print("Running YOLOv5 detection...")
        results = model(image)

        # ตรวจสอบผลลัพธ์ที่ได้
        predictions = results.pandas().xyxy[0].to_dict(orient="records")
        
        # กรองผลลัพธ์ตามวัตถุที่ต้องการ
        filtered_predictions = [pred for pred in predictions if pred['name'] in target_objects]

        # ตรวจสอบให้มั่นใจว่าไม่มีผลลัพธ์
        if not filtered_predictions:
            return JSONResponse(status_code=400, content={"message": "No target objects detected"})

        # การจัดรูปแบบผลลัพธ์เป็น JSON ที่ถูกต้อง
        result_data = {
            "objects": [results.names[i] for i in range(len(results.names))],  # รายชื่อวัตถุที่ตรวจจับได้
            "predictions": filtered_predictions  # แสดงเฉพาะผลที่กรองแล้ว
        }

        # ส่งข้อมูลกลับเป็น JSONResponse
        print("Detection completed:", result_data)
        return JSONResponse(content=result_data)

    except Exception as e:
        # แสดงข้อผิดพลาด
        print("Error:", str(e))
        return JSONResponse(status_code=500, content={"message": "An error occurred during detection."})

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)