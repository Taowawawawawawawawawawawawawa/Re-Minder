package reminder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    
            // Process file...
            byte[] fileBytes = file.getBytes();
            if (fileBytes.length > 5000000) {
                response.put("status", "error");
                response.put("message", "File size is too large");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
    
            // Send file to YOLO API
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
    
            // Log YOLO API response
            System.out.println("YOLO API Response: " + yoloResponse.getBody());
    
            if (yoloResponse.getStatusCode() == HttpStatus.OK) {
                // Parse YOLO response
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> yoloResponseBody = objectMapper.readValue(yoloResponse.getBody(), Map.class);
    
                // Extract detected objects and filter by confidence
                List<Map<String, Object>> predictions = (List<Map<String, Object>>) yoloResponseBody.get("predictions");
                System.out.println("Predictions: " + predictions);
    
                List<String> detectedObjects = new ArrayList<>();
                for (Map<String, Object> prediction : predictions) {
                    System.out.println("Prediction Entry: " + prediction);
                    double confidence = (double) prediction.get("confidence");
                    if (confidence > 0.5) { // Filter by confidence threshold
                        String label = (String) prediction.get("name"); // Correctly cast 'name'
                        detectedObjects.add(label);
                    }
                }
    
                System.out.println("Filtered Detected Objects: " + detectedObjects);
    
                if (detectedObjects.isEmpty()) {
                    response.put("status", "success");
                    response.put("message", "No objects detected");
                } else {
                    response.put("status", "success");
                    response.put("detectedObjects", detectedObjects);
                }
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Failed to process image, YOLO API error");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "Error processing request: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
