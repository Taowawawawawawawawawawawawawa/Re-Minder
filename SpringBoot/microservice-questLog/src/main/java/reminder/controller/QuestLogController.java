package reminder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import reminder.dto.QuestDTO;
import reminder.dto.QuestLogDTO;
// import reminder.Service.QuestLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questlogs")
public class QuestLogController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/submit-image")
    public ResponseEntity<Map<String, Object>> submitQuestImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("questId") Long questId) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (file.isEmpty()) {
                response.put("status", "error");
                response.put("message", "No file uploaded");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            byte[] fileBytes = file.getBytes();
            if (fileBytes.length > 5000000) {
                response.put("status", "error");
                response.put("message", "File size is too large");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // 1. ดึงข้อมูลเควสจาก API
            String currentQuestUrl = "http://localhost:8202/quests/" + questId;
            ResponseEntity<QuestDTO> currentQuestResponse = restTemplate.getForEntity(currentQuestUrl, QuestDTO.class);

            if (currentQuestResponse.getStatusCode() != HttpStatus.OK) {
                response.put("status", "error");
                response.put("message", "ไม่สามารถดึงข้อมูลเควส");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            QuestDTO currentQuest = currentQuestResponse.getBody();
            String targetObject = currentQuest.getTargetObject(); // เป้าหมายจากข้อมูลเควส

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

            ResponseEntity<String> yoloResponse = restTemplate.exchange(
                    yolov5ApiUrl, HttpMethod.POST, request, String.class);

            if (yoloResponse.getStatusCode() == HttpStatus.OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> yoloResponseBody = objectMapper.readValue(yoloResponse.getBody(), Map.class);

                List<Map<String, Object>> predictions = (List<Map<String, Object>>) yoloResponseBody.get("predictions");

                List<String> detectedObjects = new ArrayList<>();
                for (Map<String, Object> prediction : predictions) {
                    double confidence = (double) prediction.get("confidence");
                    if (confidence > 0.5) {
                        String label = (String) prediction.get("name");
                        detectedObjects.add(label);
                    }
                }

                // String targetObject = ; // เป้าหมายของภารกิจที่ต้องการ
                if (detectedObjects.isEmpty()) {
                    response.put("status", "success");
                    response.put("message", "ไม่พบวัตถุในภาพ");
                } else if (detectedObjects.contains(targetObject)) {
                    response.put("status", "success");
                    response.put("message", "เควสสำเร็จ");
                    response.put("questStatus", "completed");
                } else {
                    response.put("status", "success");
                    response.put("message", "วัตถุที่พบไม่ตรงกับเป้าหมาย");
                    response.put("questStatus", "not_completed");
                }
            } else {
                response.put("status", "error");
                response.put("message", "การตรวจจับล้มเหลว");
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "เกิดข้อผิดพลาด: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // @GetMapping
    // public ResponseEntity<List<QuestLogDTO>> getAllQuestLogs() {
    //     Map<String, Object> response = new HashMap<>();
    //     try {
    //         // เรียกบริการหรือ repository เพื่อดึงข้อมูล Quest Logs
    //         List<QuestLogDTO> questLogs = QuestLogService.getAllQuestLogs(); // Service ที่คุณต้องสร้าง
    //         if (questLogs.isEmpty()) {
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         }
    //         return new ResponseEntity<>(questLogs, HttpStatus.OK);
    //     } catch (Exception e) {
    //         response.put("status", "error");
    //         response.put("message", "เกิดข้อผิดพลาด: " + e.getMessage());
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
}
