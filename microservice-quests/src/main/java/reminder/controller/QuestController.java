package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.domain.Quest;
import reminder.dto.QuestDTO;
import reminder.dto.mapper.QuestMapper;
import reminder.repository.QuestRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quests")
public class QuestController {

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private QuestMapper questMapper;

    @GetMapping("/{id}")
    public ResponseEntity<QuestDTO> getQuestById(@PathVariable Long id) {
        Optional<Quest> quest = questRepository.findById(id);
        if (quest.isPresent()) {
            QuestDTO questDTO = new QuestDTO();
            questMapper.updateQuestFromEntity(quest.get(), questDTO);
            return new ResponseEntity<>(questDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuestDTO>> getAllQuests() {
        List<Quest> quests = questRepository.findAll();
        List<QuestDTO> questDTOs = quests.stream().map(quest -> {
            QuestDTO dto = new QuestDTO();
            questMapper.updateQuestFromEntity(quest, dto);
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(questDTOs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuest(@RequestBody QuestDTO questDTO) {
        try {
            Quest quest = new Quest();
            questMapper.updateQuestFromDto(questDTO, quest);
            questRepository.save(quest);
            return new ResponseEntity<>("Quest created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the quest.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
