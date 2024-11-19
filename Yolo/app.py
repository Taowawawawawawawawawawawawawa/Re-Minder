from fastapi import FastAPI, File, UploadFile, HTTPException
from fastapi.responses import JSONResponse
from fastapi.middleware.cors import CORSMiddleware
import io
from PIL import Image
import torch
    
# สร้าง FastAPI app
app = FastAPI()

print("Loading YOLOv5 model...")
model = torch.hub.load('ultralytics/yolov5', 'yolov5s')
print("YOLOv5 model loaded successfully.")

# ตั้งค่า CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:3000"],  # ตั้งค่า React URL
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# โหลด YOLOv5 Model
model = torch.hub.load('ultralytics/yolov5', 'yolov5s')

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

        # ตรวจสอบผลลัพธ์
        results_data = {
            "objects": results.names,  # รายชื่อวัตถุที่ตรวจจับได้
            "predictions": results.pandas().xyxy[0].to_dict(orient="records")  # แปลงเป็น JSON-ready
        }
        print("Detection completed:", results_data)
        return JSONResponse(content=results_data)

    except Exception as e:
        # แสดงข้อผิดพลาด
        print("Error:", str(e))
        return JSONResponse(status_code=500, content={"message": str(e)})

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)