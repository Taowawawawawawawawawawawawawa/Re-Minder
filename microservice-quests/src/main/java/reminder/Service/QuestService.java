package reminder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reminder.dto.QuestDTO;
import reminder.dto.mapper.QuestMapper;
import reminder.domain.Quest;
import reminder.repository.QuestRepository;

import java.util.List;

@Service
public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private QuestMapper questMapper;

    public void createQuest(QuestDTO questDTO) {
        Quest quest = new Quest();
        questMapper.updateQuestFromDto(questDTO, quest);
        questRepository.save(quest); // ไม่ต้องตั้งค่า questId เพราะมันจะถูกสร้างโดยฐานข้อมูล
    }

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }
}