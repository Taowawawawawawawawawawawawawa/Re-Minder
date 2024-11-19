package reminder.dto.mapper;

import reminder.domain.QuestLog;
import reminder.dto.QuestLogDTO;
import org.springframework.stereotype.Component;

@Component
public class QuestLogMapper {

    // Convert QuestLogDTO to QuestLog entity
    public QuestLog toEntity(QuestLogDTO dto) {
        QuestLog questLog = new QuestLog();
        questLog.setId(dto.getId());
        questLog.setUserId(dto.getUserId());
        questLog.setQuestId(dto.getQuestId());
        questLog.setStatus(dto.getStatus());
        questLog.setImageUrl(dto.getImageUrl());
        questLog.setQuestName(dto.getQuestName());
        questLog.setQuestDescription(dto.getQuestDescription());
        questLog.setBerylReward(dto.getBerylReward());
        questLog.setDifficulty(dto.getDifficulty());
        questLog.setPointReward(dto.getPointReward());
        questLog.setSubmitText(dto.getSubmitText());
        questLog.setSubmissionDate(dto.getSubmissionDate());
        questLog.getDetail(dto.getDetail());
        questLog.setDetail(dto.setDetail());
        return questLog;
    }

    // Update QuestLogDTO from QuestLog entity
    public void updateQuestLogFromEntity(QuestLog entity, QuestLogDTO dto) {
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setQuestId(entity.getQuestId());
        dto.setStatus(entity.getStatus());
        dto.setImageUrl(entity.getImageUrl());
        dto.setQuestName(entity.getQuestName());
        dto.setQuestDescription(entity.getQuestDescription());
        dto.setBerylReward(entity.getBerylReward());
        dto.setDifficulty(entity.getDifficulty());
        dto.setPointReward(entity.getPointReward());
        dto.setSubmitText(entity.getSubmitText());
        dto.setSubmissionDate(entity.getSubmissionDate());
        dto.getDetail(entity.getDetail());
        dto.setDetail(entity.setDetail());
    }
}
