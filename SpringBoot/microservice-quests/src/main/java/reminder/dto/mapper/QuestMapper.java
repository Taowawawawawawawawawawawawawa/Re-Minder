package reminder.dto.mapper;


import org.springframework.stereotype.Component;
import reminder.domain.Quest;
import reminder.dto.QuestDTO;

@Component
public class QuestMapper {
    public QuestDTO toDTO(Quest quest) {
        QuestDTO dto = new QuestDTO();
        dto.setQuestId(quest.getQuestId());
        dto.setQuestName(quest.getQuestName());
        dto.setQuestDescription(quest.getQuestDescription());
        dto.setDifficulty(quest.getDifficulty());
        dto.setBerylReward(quest.getBerylReward());
        dto.setPointReward(quest.getPointReward());
        dto.setQuestSubmitMethod(quest.getQuestSubmitMethod());
        dto.setSuitableMBTI(quest.getSuitableMBTI());
        dto.setAvailableTime(quest.getAvailableTime());
        dto.setTargetObject(quest.getTargetObject()); // Map targetObject
        return dto;
    }

    public Quest toEntity(QuestDTO dto) {
        Quest quest = new Quest();
        quest.setQuestId(dto.getQuestId());
        quest.setQuestName(dto.getQuestName());
        quest.setQuestDescription(dto.getQuestDescription());
        quest.setDifficulty(dto.getDifficulty());
        quest.setBerylReward(dto.getBerylReward());
        quest.setPointReward(dto.getPointReward());
        quest.setQuestSubmitMethod(dto.getQuestSubmitMethod());
        quest.setSuitableMBTI(dto.getSuitableMBTI());
        quest.setAvailableTime(dto.getAvailableTime());
        quest.setTargetObject(dto.getTargetObject()); // Map targetObject
        return quest;
    }
    
}