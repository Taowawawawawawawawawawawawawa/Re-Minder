package reminder.controller;

import reminder.domain.QuestLog;
import reminder.dto.QueslogtDTO;
import reminder.dto.mapper.QuestLogMapper;
import reminder.repository.QuestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questlogs")
@CrossOrigin(origins = "http://localhost:3000") // ควบคุม CORS ให้ถูกต้อง
public class QuestLogController {

    @Autowired
    private QuestLogRepository questLogRepository;

    @Autowired
    private QuestLogMapper questLogMapper;

    @PostMapping("/create")
    public ResponseEntity<String> createQuestLog(@RequestBody QueslogtDTO questLogDTO) {
        if (questLogDTO == null) {
            return new ResponseEntity<>("Request body is empty.", HttpStatus.BAD_REQUEST);
        }

        try {
            QuestLog questLog = questLogMapper.questLogDTOToQuest(questLogDTO);
            questLogRepository.save(questLog);
            return new ResponseEntity<>("Quest Log created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // log the exception
            return new ResponseEntity<>("An error occurred while creating the Quest Log.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<QueslogtDTO>> getAllQuestLogs() {
        List<QuestLog> questLogs = questLogRepository.findAll();
        if (questLogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<QueslogtDTO> questLogDTOs = questLogs.stream()
                .map(questLog -> questLogMapper.questToQuestLogDTO(questLog))
                .collect(Collectors.toList());
        return new ResponseEntity<>(questLogDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QueslogtDTO> getQuestLogById(@PathVariable Long id) {
        Optional<QuestLog> questLog = questLogRepository.findById(id);
        if (questLog.isPresent()) {
            QueslogtDTO questLogDTO = questLogMapper.questToQuestLogDTO(questLog.get());
            return new ResponseEntity<>(questLogDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // เพิ่มฟังก์ชันในการอัปเดตสถานะของ QuestLog
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestBody String status) {
        Optional<QuestLog> questLog = questLogRepository.findById(id);
        if (questLog.isPresent()) {
            QuestLog updatedQuestLog = questLog.get();
            updatedQuestLog.setStatus(status);
            questLogRepository.save(updatedQuestLog);
            return new ResponseEntity<>("Quest Log status updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Quest Log not found.", HttpStatus.NOT_FOUND);
        }
    }
}