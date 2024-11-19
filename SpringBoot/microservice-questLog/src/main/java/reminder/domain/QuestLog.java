package reminder.domain;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class QuestLog {
    @Id
    private Long id;

    private String imageUrl; // เพิ่มฟิลด์นี้

    private Long questId;
    private Long userId;
    private String status;
    private String questName;
    private String questDescription;
    private Integer berylReward;
    private Integer difficulty;
    private Integer pointReward;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl; // เพิ่ม setter นี้
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
}