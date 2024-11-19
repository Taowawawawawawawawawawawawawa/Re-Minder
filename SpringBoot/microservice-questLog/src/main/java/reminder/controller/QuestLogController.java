package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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