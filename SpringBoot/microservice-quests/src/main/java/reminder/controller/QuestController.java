package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.dto.QuestDTO;
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

    @PostMapping("/create")
    public ResponseEntity<String> createQuest(@RequestBody QuestDTO questDTO) {
        try {
            questService.createQuest(questDTO);
            return new ResponseEntity<>("Quest created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating quest: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{questId}")
    public ResponseEntity<QuestDTO> getQuestById(@PathVariable Long questId) {
        try {
            // Retrieve quest by ID using the service
            Optional<Quest> questDTO = questRepository.findById(questId);
            
            // If quest is found, return it
            if (questDTO != null) {
                return new ResponseEntity<>(questDTO, HttpStatus.OK);
            } else {
                // Return not found if quest does not exist
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Return error message if there was an exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public List<Quest> getAllQuests() {
        return questService.getAllQuests();
    }
}