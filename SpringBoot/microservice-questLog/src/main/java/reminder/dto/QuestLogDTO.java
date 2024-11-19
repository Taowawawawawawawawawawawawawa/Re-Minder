package reminder.dto;

import java.time.LocalDateTime;

public class QuestLogDTO {
    


    private Long userId;
    private Long questId;
    private String status;

    private String questDescription;
    private Integer berylReward;

    private String message; // ตรวจสอบว่ามี field นี้หรือไม่

    // Getter และ Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    private Integer difficulty;
    private Integer pointReward;
    private LocalDateTime submissionDate;
    private String detail;


    public String getSubmitText() {
        return submitText;
    }
    public void setSubmitText(String submitText) {
        this.submitText = submitText;
    }
    private String submitText;


    // Getters and Setters
    public Long getId() {
        return userId;
    }
    private String questName;
    private String imageUrl;




    public String getQuestName() {
        return questName;
    }
    public void setQuestName(String questName) {
        this.questName = questName;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }
    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }
    public String setDetail() {

        throw new UnsupportedOperationException("Unimplemented method 'setDetail'");
    }
    public void getDetail(String detail2) {
 
        throw new UnsupportedOperationException("Unimplemented method 'getDetail'");
    }
    public String getQuestDescription() {

        throw new UnsupportedOperationException("Unimplemented method 'getQuestDescription'");
    }
    public Integer getBerylReward() {

        throw new UnsupportedOperationException("Unimplemented method 'getBerylReward'");
    }
    public Integer getDifficulty() {
  
        throw new UnsupportedOperationException("Unimplemented method 'getDifficulty'");
    }
    public Integer getPointReward() {

        throw new UnsupportedOperationException("Unimplemented method 'getPointReward'");
    }
    public void setQuestDescription(String questDescription2) {

        throw new UnsupportedOperationException("Unimplemented method 'setQuestDescription'");
    }
    public void setBerylReward(Integer berylReward2) {

        throw new UnsupportedOperationException("Unimplemented method 'setBerylReward'");
    }
    public void setDifficulty(Integer difficulty2) {

        throw new UnsupportedOperationException("Unimplemented method 'setDifficulty'");
    }
    public void setPointReward(Integer pointReward2) {

        throw new UnsupportedOperationException("Unimplemented method 'setPointReward'");
    }

    // Getter, Setter, Constructor
}