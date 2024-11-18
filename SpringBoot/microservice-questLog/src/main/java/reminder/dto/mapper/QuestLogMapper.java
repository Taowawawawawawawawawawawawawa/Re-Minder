package reminder.dto.mapper;

import reminder.domain.QuestLog;
import reminder.dto.QuestLogDTO;
import org.springframework.stereotype.Component;

@Component
public class QuestLogMapper {

    // Convert QuestLogDTO to QuestLog entity
    public QuestLog toEntity(QuestLogDTO dto) {
        QuestLog questLog = new QuestLog();
        questLog.setUserId(dto.getUserId());
        questLog.setQuestId(dto.getQuestId());
        questLog.setStatus(dto.getStatus());
        questLog.setSubmissionDate(dto.getSubmissionDate());
        return questLog;
    }

    // Update existing QuestLog entity from DTO
    public void updateQuestLogFromEntity(QuestLog entity, QuestLogDTO dto) {
        dto.setUserId(entity.getUserId());
        dto.setQuestId(entity.getQuestId());
        dto.setStatus(entity.getStatus());
        dto.setSubmissionDate(entity.getSubmissionDate());
    }
}