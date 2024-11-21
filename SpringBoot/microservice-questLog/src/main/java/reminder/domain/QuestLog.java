package reminder.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "quest_log") // Use lowercase table name to match the SQL
public class QuestLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generate IDs
    private Long id ;


    @Lob
    @Column(name = "image_url")
    private String imageUrl; // เพิ่มฟิลด์นี้

    private String submitText;
    private Long questId;
    private Long userId;
    private String status;

    private String questName;
    private String questDescription;
    private Integer berylReward;
    private Integer difficulty;
    private Integer pointReward;
    private String detail;

    private String message; // ตรวจสอบว่ามี field นี้หรือไม่

    // Getter และ Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private LocalDateTime submissionDate;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getSubmitText() {
        return submitText;
    }

    public void setSubmitText(String submitText) {
        this.submitText = submitText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Object getSubmittedImage() {

        throw new UnsupportedOperationException("Unimplemented method 'getSubmittedImage'");
    }

    public void setRejectionReason(String reason) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRejectionReason'");
    }
}