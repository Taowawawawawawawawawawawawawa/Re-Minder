package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.dto.QuestDTO;
import reminder.dto.mapper.QuestMapper;
import reminder.repository.QuestRepository;
import reminder.Service.QuestService;
import reminder.domain.Quest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quests")
public class QuestController {
    @Autowired
    private QuestService questService;

    @Autowired
    private QuestRepository questRepository;

    @Autowired QuestMapper questMapper;

    @PostMapping("/create")
    public ResponseEntity<String> createQuest(@RequestBody QuestDTO questDTO) {
        try {
            questService.createQuest(questDTO);
            return new ResponseEntity<>("Quest created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating quest: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public List<Quest> getAllQuests() {
        return questService.getAllQuests();
    }

    @GetMapping("/{questid}")
    public ResponseEntity<QuestDTO> getUserByquestId(@PathVariable Long questid) {
        Optional<Quest> quest = questRepository.findById(questid);
        if (quest.isPresent()) {
            QuestDTO userDTO = questMapper.toDTO(quest.get());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}