package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType; // เพิ่มการ import MediaType
import org.springframework.web.multipart.MultipartFile;

import reminder.domain.QuestLog;
import reminder.dto.QuestLogDTO;
import reminder.dto.mapper.QuestLogMapper;
import reminder.repository.QuestLogRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questlogs")
public class QuestLogController {

    @Autowired
    private QuestLogRepository questLogRepository;

    @Autowired
    private QuestLogMapper questLogMapper;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/submit-image")
    public ResponseEntity<String> submitQuestImage(@RequestParam("file") MultipartFile file) {
        try {
            // URL ของ YOLOv5 API
            String yolov5ApiUrl = "http://localhost:8000/detect";

            // สร้างข้อมูลที่ใช้ส่ง
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });

            // สร้าง HttpHeaders และ HttpEntity สำหรับ request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);  // ใช้ MediaType แทน String
            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

            // ส่งข้อมูลไปยัง YOLOv5 API
            ResponseEntity<String> response = restTemplate.exchange(
                    yolov5ApiUrl,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            // ส่งผลลัพธ์กลับไปยัง frontend
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error processing image: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get QuestLog by ID
    @GetMapping("/{id}")
    public ResponseEntity<QuestLogDTO> getQuestLogById(@PathVariable Long id) {
        Optional<QuestLog> questLog = questLogRepository.findById(id);
        if (questLog.isPresent()) {
            QuestLogDTO questLogDTO = new QuestLogDTO();
            questLogMapper.updateQuestLogFromEntity(questLog.get(), questLogDTO);
            return new ResponseEntity<>(questLogDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get All QuestLogs
    @GetMapping("/all")
    public ResponseEntity<List<QuestLogDTO>> getAllQuestLogs() {
        List<QuestLog> questLogs = questLogRepository.findAll();
        List<QuestLogDTO> questLogDTOs = questLogs.stream().map(questLog -> {
            QuestLogDTO dto = new QuestLogDTO();
            questLogMapper.updateQuestLogFromEntity(questLog, dto);
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(questLogDTOs, HttpStatus.OK);
    }

    // Create QuestLog
    @PostMapping("/create")
    public ResponseEntity<String> createQuestLog(@RequestBody QuestLogDTO questLogDTO) {
        try {
            // Map QuestLogDTO to Entity
            QuestLog questLog = questLogMapper.toEntity(questLogDTO);
            // Save QuestLog to the database
            questLogRepository.save(questLog);
            return new ResponseEntity<>("QuestLog created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating QuestLog: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update QuestLog Status (Pending, Approved, Rejected)
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateQuestLogStatus(@PathVariable Long id, @RequestParam String status) {
        // Validate status input (should be Pending, Approved, or Rejected)
        if (!status.equals("Pending") && !status.equals("Approved") && !status.equals("Rejected")) {
            return new ResponseEntity<>("Invalid status value.", HttpStatus.BAD_REQUEST);
        }

        Optional<QuestLog> questLog = questLogRepository.findById(id);
        if (questLog.isPresent()) {
            QuestLog updatedLog = questLog.get();
            updatedLog.setStatus(status); // Update status of the QuestLog
            questLogRepository.save(updatedLog);
            return new ResponseEntity<>("QuestLog status updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("QuestLog not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Delete QuestLog
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteQuestLog(@PathVariable Long id) {
        try {
            questLogRepository.deleteById(id);
            return new ResponseEntity<>("QuestLog deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete QuestLog: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get QuestLogs with "PENDING" status
    @GetMapping("/pending")
    public ResponseEntity<List<QuestLogDTO>> getPendingQuestLogs() {
        List<QuestLog> pendingLogs = questLogRepository.findByStatus("PENDING"); // Use the repository method
        List<QuestLogDTO> pendingLogDTOs = pendingLogs.stream()
                .map(questLog -> {
                    QuestLogDTO dto = new QuestLogDTO();
                    questLogMapper.updateQuestLogFromEntity(questLog, dto);
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(pendingLogDTOs, HttpStatus.OK);
    }

}