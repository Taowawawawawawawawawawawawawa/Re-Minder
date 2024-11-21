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
        quest.setQuestName(questDTO.getQuestName());
        quest.setQuestDescription(questDTO.getQuestDescription());
        quest.setDifficulty(questDTO.getDifficulty());
        quest.setBerylReward(questDTO.getBerylReward());
        quest.setPointReward(questDTO.getPointReward());
        quest.setQuestSubmitMethod(questDTO.getQuestSubmitMethod());
        quest.setTargetObject(questDTO.getTargetObject());
        quest.setSuitableMBTI(questDTO.getSuitableMBTI());
        quest.setAvailableTime(questDTO.getAvailableTime());
        questRepository.save(quest);
    }

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public QuestDTO getQuestById(Long questId) {
        return questRepository.findById(questId)
                .map(quest -> {
                    QuestDTO questDTO = new QuestDTO();
                    quest = questMapper.toEntity(questDTO);
                    return questDTO;
                })
                .orElse(null);
    }
    
}