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
            // ตรวจสอบข้อมูลที่ได้รับ
            if (file.isEmpty()) {
                return new ResponseEntity<>("No file uploaded", HttpStatus.BAD_REQUEST);
            }

            if (questId == null) {
                return new ResponseEntity<>("Quest ID is missing", HttpStatus.BAD_REQUEST);
            }

            System.out.println("Received questId: " + questId);
            System.out.println("Received file name: " + file.getOriginalFilename());

            // ดึงข้อมูล Quest จาก microservice Quest
            ResponseEntity<QuestDTO> questResponse = restTemplate.getForEntity(
                "http://microservice-quest/quests/" + questId,
                QuestDTO.class
            );

            if (questResponse.getStatusCode() != HttpStatus.OK) {
                return new ResponseEntity<>("Quest not found", HttpStatus.NOT_FOUND);
            }

            QuestDTO quest = questResponse.getBody();
            String targetObject = quest.getTargetObject();
            System.out.println("Target object: " + targetObject);

            // Logic สำหรับการส่งภาพไปยัง YOLO API
            String yolov5ApiUrl = "http://localhost:8000/detect";
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> yoloResponse = restTemplate.exchange(
                    yolov5ApiUrl,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            if (yoloResponse.getStatusCode() == HttpStatus.OK) {
                String detectedObject = yoloResponse.getBody();
                if (detectedObject.contains(targetObject)) {
                    return new ResponseEntity<>("Object detected successfully!", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Target object not detected", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Failed to process image", yoloResponse.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error processing request: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}