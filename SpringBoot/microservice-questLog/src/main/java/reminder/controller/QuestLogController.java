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
import reminder.domain.QuestLog;
import reminder.repository.QuestLogRepository;
import reminder.dto.QuestDTO;
import reminder.dto.QuestLogDTO;
import reminder.dto.mapper.QuestLogMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questlogs")
public class QuestLogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private QuestLogRepository questLogRepository; // Repository to update the quest log

    @Autowired
    private QuestLogMapper questLogsMapper;

    @GetMapping("/all")
    public ResponseEntity<List<QuestLogDTO>> getAllBerylSells() {
        List<QuestLog> questLogs = questLogRepository.findAll();
        List<QuestLogDTO> logDTOs = questLogs.stream().map(log -> {
            QuestLogDTO dto = new QuestLogDTO();
            questLogsMapper.updateQuestLogFromEntity(log, dto);
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(logDTOs, HttpStatus.OK);
    }

    @PostMapping("/submit-image")
    public ResponseEntity<Map<String, Object>> submitQuestImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("questId") Long questId,
            @RequestParam("userId") Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Validate input
            if (file == null || file.isEmpty()) {
                response.put("status", "error");
                response.put("message", "No file uploaded");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if (questId == null || userId == null) {
                response.put("status", "error");
                response.put("message", "Quest ID or User ID is missing");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Fetch the quest details from the Quest microservice
            String currentQuestUrl = "http://localhost:8202/quests/" + questId;
            ResponseEntity<QuestDTO> currentQuestResponse = restTemplate.getForEntity(currentQuestUrl, QuestDTO.class);
            if (currentQuestResponse.getStatusCode() != HttpStatus.OK || currentQuestResponse.getBody() == null) {
                response.put("status", "error");
                response.put("message", "Unable to fetch quest details");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            QuestDTO currentQuest = currentQuestResponse.getBody();
            String targetObject = currentQuest.getTargetObject();
            if (targetObject == null || targetObject.isEmpty()) {
                response.put("status", "error");
                response.put("message", "Quest target object is not defined for Quest ID: " + questId);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

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

            ResponseEntity<String> yoloResponse = restTemplate.exchange(yolov5ApiUrl, HttpMethod.POST, request,
                    String.class);

            // Initialize the QuestLog entity
            QuestLog questLog = new QuestLog();
            questLog.setQuestId(questId);
            questLog.setUserId(userId);
            questLog.setImageUrl(file.getOriginalFilename());
            questLog.setQuestName(currentQuest.getQuestName());
            questLog.setQuestDescription(currentQuest.getQuestDescription());
            questLog.setBerylReward(currentQuest.getBerylReward());
            questLog.setDifficulty(currentQuest.getDifficulty());
            questLog.setPointReward(currentQuest.getPointReward());
            questLog.setSubmissionDate(java.time.LocalDateTime.now());
            questLog.setStatus("PENDING"); // Default status

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
                    questLog.setStatus("PENDING");
                    response.put("status", "success");
                    response.put("message", "No objects detected in the image");
                    response.put("questStatus", "PENDING");
                } else if (detectedObjects.contains(targetObject)) {
                    questLog.setStatus("SUCCESS");
                    response.put("status", "success");
                    response.put("message", "Quest completed successfully");
                    response.put("questStatus", "SUCCESS");
                } else {
                    questLog.setStatus("FAILED");
                    response.put("status", "success");
                    response.put("message", "Detected objects do not match the target");
                    response.put("questStatus", "FAILED");
                }
            } else {
                questLog.setStatus("PENDING");
                response.put("status", "error");
                response.put("message", "Object detection failed");
            }

            // Save the QuestLog to the database
            questLogRepository.save(questLog);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/submit-text")
    public ResponseEntity<Map<String, Object>> submitQuestText(
            @RequestParam("questId") Long questId,
            @RequestParam("userId") Long userId,
            @RequestParam("text") String text) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Validate input
            if (text == null || text.isEmpty()) {
                response.put("status", "error");
                response.put("message", "Text cannot be empty");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if (questId == null || userId == null) {
                response.put("status", "error");
                response.put("message", "Quest ID or User ID is missing");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Fetch the quest details from the Quest microservice
            String currentQuestUrl = "http://localhost:8202/quests/" + questId;
            ResponseEntity<QuestDTO> currentQuestResponse = restTemplate.getForEntity(currentQuestUrl, QuestDTO.class);
            if (currentQuestResponse.getStatusCode() != HttpStatus.OK || currentQuestResponse.getBody() == null) {
                response.put("status", "error");
                response.put("message", "Unable to fetch quest details");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            QuestDTO currentQuest = currentQuestResponse.getBody();

            // Initialize the QuestLog entity
            QuestLog questLog = new QuestLog();
            questLog.setQuestId(questId);
            questLog.setUserId(userId);
            questLog.setQuestName(currentQuest.getQuestName());
            questLog.setQuestDescription(currentQuest.getQuestDescription());
            questLog.setBerylReward(currentQuest.getBerylReward());
            questLog.setDifficulty(currentQuest.getDifficulty());
            questLog.setPointReward(currentQuest.getPointReward());
            questLog.setSubmissionDate(java.time.LocalDateTime.now());
            questLog.setStatus("PENDING"); // Default status awaiting admin review
            questLog.setSubmitText(text); // Save the submitted text

            // Save the QuestLog to the database
            questLogRepository.save(questLog);

            response.put("status", "success");
            response.put("message", "Quest submission is pending admin review");
            response.put("questStatus", "PENDING");

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "An error occurred: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<QuestLogDTO>> getPendingQuests() {
        try {
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{questId}/approve")
    public ResponseEntity<String> approveQuest(@PathVariable Long questId) {
        QuestLog questLog = questLogRepository.findById(questId)
                .orElse(null);

        if (questLog == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quest not found");
        }

        questLog.setStatus("APPROVED");
        questLogRepository.save(questLog);
        return ResponseEntity.ok("Quest approved successfully");
    }

    @PatchMapping("/{questId}/reject")
    public ResponseEntity<String> rejectQuest(
            @PathVariable Long questId,
            @RequestBody Map<String, String> requestBody) {
        QuestLog questLog = questLogRepository.findById(questId)
                .orElse(null);

        if (questLog == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quest not found");
        }

        String reason = requestBody.get("reason");
        if (reason == null || reason.isEmpty()) {
            return ResponseEntity.badRequest().body("Reason for rejection is required");
        }

        questLog.setStatus("FAILED");
        questLog.setDetail(reason);
        questLogRepository.save(questLog);
        return ResponseEntity.ok("Quest rejected successfully");
    }

}