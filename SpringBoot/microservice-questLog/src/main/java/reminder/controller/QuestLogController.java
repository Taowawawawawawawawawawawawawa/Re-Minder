package reminder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import reminder.dto.QuestDTO;
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
            // Validate input
            if (file == null || file.isEmpty()) {
                response.put("status", "error");
                response.put("message", "No file uploaded");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if (questId == null) {
                response.put("status", "error");
                response.put("message", "Quest ID is missing");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
    
            // Fetch the quest details from the Quest microservice
            String currentQuestUrl = "http://localhost:8202/quests/" + questId;
            ResponseEntity<QuestDTO> currentQuestResponse = restTemplate.getForEntity(currentQuestUrl, QuestDTO.class);
            if (currentQuestResponse.getStatusCode() != HttpStatus.OK || currentQuestResponse.getBody() == null) {
                response.put("status", "error");
                response.put("message", "Unable to fetch quest details. Response: " + currentQuestResponse.getStatusCode());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
    
            QuestDTO currentQuest = currentQuestResponse.getBody();
            String targetObject = currentQuest.getTargetObject();
            if (targetObject == null || targetObject.isEmpty()) {
                response.put("status", "error");
                response.put("message", "Quest target object is not defined for Quest ID: " + questId);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
    
            // Debug log for targetObject
            System.out.println("Target Object: " + targetObject);
    
            // Send the image to the YOLO detection API
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
    
            ResponseEntity<String> yoloResponse = restTemplate.exchange(yolov5ApiUrl, HttpMethod.POST, request, String.class);
    
            // Process YOLO response
            if (yoloResponse.getStatusCode() == HttpStatus.OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> yoloResponseBody = objectMapper.readValue(yoloResponse.getBody(), Map.class);
    
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> predictions = (List<Map<String, Object>>) yoloResponseBody.get("predictions");
    
                List<String> detectedObjects = new ArrayList<>();
                for (Map<String, Object> prediction : predictions) {
                    double confidence = (double) prediction.get("confidence");
                    if (confidence > 0.5) {
                        detectedObjects.add((String) prediction.get("name"));
                    }
                }
    
                if (detectedObjects.isEmpty()) {
                    response.put("status", "success");
                    response.put("message", "No objects detected in the image");
                } else if (detectedObjects.contains(targetObject)) {
                    response.put("status", "success");
                    response.put("message", "Quest completed successfully");
                    response.put("questStatus", "completed");
                } else {
                    response.put("status", "success");
                    response.put("message", "Detected objects do not match the target");
                    response.put("questStatus", "not_completed");
                }
            } else {
                response.put("status", "error");
                response.put("message", "Object detection failed");
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
