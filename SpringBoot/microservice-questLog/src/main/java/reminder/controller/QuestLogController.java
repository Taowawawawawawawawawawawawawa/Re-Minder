package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import reminder.dto.QuestDTO;

@RestController
@RequestMapping("/questlogs")
public class QuestLogController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/submit-image")
public ResponseEntity<String> submitQuestImage(
        @RequestParam("file") MultipartFile file,
        @RequestParam("questId") Long questId) {
    try {
        if (file.isEmpty()) {
            return new ResponseEntity<>("No file uploaded", HttpStatus.BAD_REQUEST);
        }

        // แปลง MultipartFile เป็น byte array
        byte[] fileBytes = file.getBytes();

        // ตรวจสอบว่าไฟล์มีขนาดไม่เกินขีดจำกัด (optional)
        if (fileBytes.length > 5000000) {  // เช่น จำกัดขนาดไฟล์ไม่เกิน 5MB
            return new ResponseEntity<>("File size is too large", HttpStatus.BAD_REQUEST);
        }

        // ส่งไฟล์ไปยัง YOLO API
        String yolov5ApiUrl = "http://localhost:8000/detect";
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(fileBytes) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        // การตอบกลับจาก YOLO API เป็น String
        ResponseEntity<String> yoloResponse = restTemplate.exchange(
                yolov5ApiUrl,
                HttpMethod.POST,
                request,
                String.class  // ใช้ String แทนที่จะใช้ JSON
        );

        if (yoloResponse.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>("Object detected: " + yoloResponse.getBody(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to process image, YOLO API error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } catch (Exception e) {
        e.printStackTrace();  // แสดง error ใน log
        return new ResponseEntity<>("Error processing request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}