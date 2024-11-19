package reminder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.dto.QuestDTO;
import reminder.Service.QuestService;
import reminder.domain.Quest;

@RestController
@RequestMapping("/quests")
public class QuestController {

    @Autowired
    private QuestService questService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuest(@RequestBody QuestDTO questDTO) {
        try {
            questService.createQuest(questDTO);
            return new ResponseEntity<>("Quest created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating quest: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/start/{questId}")
    public ResponseEntity<?> startQuest(@PathVariable Long questId) {
        // เรียกใช้ Service เพื่อเปลี่ยนสถานะของเควส
        boolean updated = questService.startQuest(questId);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quest could not be started.");
        }
    }

    @GetMapping("/all")
    public List<Quest> getAllQuests() {
        return questService.getAllQuests();
    }
}