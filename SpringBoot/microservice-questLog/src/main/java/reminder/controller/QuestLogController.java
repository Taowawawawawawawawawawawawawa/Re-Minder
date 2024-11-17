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
            QuestLog questLog = new QuestLog();
            questLogMapper.updateQuestLogFromDto(questLogDTO, questLog);
            questLogRepository.save(questLog);
            return new ResponseEntity<>("QuestLog created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the quest log.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
