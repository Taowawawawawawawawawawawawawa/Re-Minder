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
        
        // ไม่ต้องแปลงจาก List<String> เป็น List<Integer
        quest.setAvailableTime(dto.getAvailableTime());
    }

    public void updateQuestFromEntity(Quest quest, QuestDTO dto) {
        dto.setQuestName(quest.getQuestName());
        dto.setQuestDescription(quest.getQuestDescription());
        dto.setDifficulty(quest.getDifficulty());
        dto.setBerylReward(quest.getBerylReward());
        dto.setPointReward(quest.getPointReward());
        dto.setQuestSubmitMethod(quest.getQuestSubmitMethod());
        dto.setAvailableTime(quest.getAvailableTime());
    }
}