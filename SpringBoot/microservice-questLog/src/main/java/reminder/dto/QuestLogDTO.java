package reminder.dto;

import java.time.LocalDateTime;

public class QuestLogDTO {

    private Long id;
    private Long userId;
    private Long questId;
    private String status;
    private String imageUrl;
    private String questName;
    private String questDescription;
    private Integer berylReward;
    private Integer difficulty;
    private Integer pointReward;
    private LocalDateTime submissionDate;
    public String getSubmitText() {
        return submitText;
    }
    public void setSubmitText(String submitText) {
        this.submitText = submitText;
    }
    private String submitText;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public void setQuestDescription(String questDescription) {
        this.questDescription = questDescription;
    }

    public Integer getBerylReward() {
        return berylReward;
    }

    public void setBerylReward(Integer berylReward) {
        this.berylReward = berylReward;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getPointReward() {
        return pointReward;
    }

    public void setPointReward(Integer pointReward) {
        this.pointReward = pointReward;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }
}
