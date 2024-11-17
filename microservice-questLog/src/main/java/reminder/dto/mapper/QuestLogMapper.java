package reminder.dto.mapper;

import reminder.domain.QuestLog;
import reminder.dto.QuestLogDTO;
import org.springframework.stereotype.Component;

@Component
public class QuestLogMapper {

    // แปลงจาก QuestLogDTO เป็น QuestLog Entity
    public QuestLog toEntity(QuestLogDTO questLogDTO) {
        QuestLog questLog = new QuestLog();
        questLog.setQuestId(questLogDTO.getQuestId());
        questLog.setUserId(questLogDTO.getUserId());
        questLog.setImageUrl(questLogDTO.getImageUrl());
        questLog.setStatus(questLogDTO.getStatus());
        questLog.setQuestName(questLogDTO.getQuestName());
        questLog.setQuestDescription(questLogDTO.getQuestDescription());
        questLog.setBerylReward(questLogDTO.getBerylReward());
        questLog.setDifficulty(questLogDTO.getDifficulty()); // difficulty เป็น int
        questLog.setPointReward(questLogDTO.getPointReward());
        return questLog;
    }

    // แปลงจาก QuestLog Entity เป็น QuestLogDTO
    public void updateQuestLogFromEntity(QuestLog questLog, QuestLogDTO questLogDTO) {
        questLogDTO.setQuestId(questLog.getQuestId());
        questLogDTO.setUserId(questLog.getUserId());
        questLogDTO.setImageUrl(questLog.getImageUrl());
        questLogDTO.setStatus(questLog.getStatus());
        questLogDTO.setQuestName(questLog.getQuestName());
        questLogDTO.setQuestDescription(questLog.getQuestDescription());
        questLogDTO.setBerylReward(questLog.getBerylReward());
        questLogDTO.setDifficulty(questLog.getDifficulty()); // difficulty เป็น int
        questLogDTO.setPointReward(questLog.getPointReward());
    }
}