package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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

    @Value("http://localhost:8202") // URL for Quests microservice
    private String questsServiceUrl;

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
    @PostMapping("/create")
    public ResponseEntity<String> createQuestLog(@RequestBody QuestLogDTO questLogDTO) {
        try {
            QuestLog questLog = questLogMapper.toEntity(questLogDTO);
            questLogRepository.save(questLog);  // บันทึกข้อมูลลงในฐานข้อมูล
            return new ResponseEntity<>("QuestLog created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating QuestLog: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update QuestLog Status
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateQuestLogStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<QuestLog> questLog = questLogRepository.findById(id);
        if (questLog.isPresent()) {
            QuestLog updatedLog = questLog.get();
            updatedLog.setStatus(status);
            questLogRepository.save(updatedLog);
            return new ResponseEntity<>("QuestLog status updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("QuestLog not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Fetch Quest Details from Quests Service
    @GetMapping("/quest/{questId}")
    public ResponseEntity<Object> getQuestDetails(@PathVariable Long questId) {
        try {
            String url = questsServiceUrl + "/quests/" + questId;
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch quest details: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete QuestLog
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteQuestLog(@PathVariable Long id) {
        try {
            questLogRepository.deleteById(id);
            return new ResponseEntity<>("QuestLog deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete QuestLog: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}