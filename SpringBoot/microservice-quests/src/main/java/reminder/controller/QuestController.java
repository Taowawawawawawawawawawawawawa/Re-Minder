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
import java.util.Random;

@RestController
@RequestMapping("/quests")
public class QuestController {

    @Autowired
    private QuestService questService;

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private QuestMapper questMapper;

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

    @GetMapping("/{questId}")
    public ResponseEntity<QuestDTO> getQuestById(@PathVariable Long questId) {
        Optional<Quest> quest = questRepository.findById(questId);
        if (quest.isPresent()) {
            QuestDTO questDTO = questMapper.toDTO(quest.get());
            return new ResponseEntity<>(questDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ฟังก์ชันใหม่: ดึงเควสแบบสุ่ม
    @GetMapping("/random")
    public ResponseEntity<QuestDTO> getRandomQuest() {
        try {
            List<Quest> quests = questRepository.findAll();
            if (quests.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // หากไม่มีเควสในระบบ
            }

            // เลือกเควสแบบสุ่ม
            Random random = new Random();
            Quest randomQuest = quests.get(random.nextInt(quests.size()));

            // แปลง Quest เป็น QuestDTO
            QuestDTO randomQuestDTO = questMapper.toDTO(randomQuest);
            return new ResponseEntity<>(randomQuestDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}