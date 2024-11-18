package reminder.dto;

import java.time.LocalDateTime;

public class QuestLogDTO {

    private Long userId;
    private Long questId;
    private String status;
    private LocalDateTime submissionDate;
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getQuestId() {
        return questId;
    }
    public void setQuestId(Long questId) {
        this.questId = questId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }
    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    // Getter, Setter, Constructor
}