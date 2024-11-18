package reminder.dto.mapper;

import org.springframework.stereotype.Component;
import reminder.domain.Quest;
import reminder.dto.QuestDTO;

@Component
public class QuestMapper {

    public void updateQuestFromDto(QuestDTO dto, Quest quest) {
        quest.setQuestName(dto.getQuestName());
        quest.setQuestDescription(dto.getQuestDescription());
        quest.setDifficulty(dto.getDifficulty());
        quest.setBerylReward(dto.getBerylReward());
        quest.setPointReward(dto.getPointReward());
        quest.setQuestSubmitMethod(dto.getQuestSubmitMethod());

        // Map the suitableMBTI field
        if (dto.getSuitableMBTI() != null) {
            quest.setSuitableMBTI(dto.getSuitableMBTI());
        }

        // Map the availableTime field
        if (dto.getAvailableTime() != null) {
            quest.setAvailableTime(dto.getAvailableTime());
        }
    }

    public void updateQuestFromEntity(Quest quest, QuestDTO dto) {
        dto.setQuestName(quest.getQuestName());
        dto.setQuestDescription(quest.getQuestDescription());
        dto.setDifficulty(quest.getDifficulty());
        dto.setBerylReward(quest.getBerylReward());
        dto.setPointReward(quest.getPointReward());
        dto.setQuestSubmitMethod(quest.getQuestSubmitMethod());

        // Map the suitableMBTI field
        if (quest.getSuitableMBTI() != null) {
            dto.setSuitableMBTI(quest.getSuitableMBTI());
        }

        // Map the availableTime field
        if (quest.getAvailableTime() != null) {
            dto.setAvailableTime(quest.getAvailableTime());
        }
    }
}
