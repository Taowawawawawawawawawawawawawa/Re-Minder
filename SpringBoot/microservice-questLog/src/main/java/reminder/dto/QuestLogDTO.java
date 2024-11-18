package reminder.dto;

public class QuestLogDTO {

    private Long questId;
    private Long userId;
    private String imageUrl;
    private String status;
    private String questName;
    private String questDescription;
    private Integer berylReward;
    private int difficulty;  // เปลี่ยนเป็น int เพื่อให้ตรงกับ Entity
    private Integer pointReward;

    // Getter และ Setter สำหรับส่วนต่าง ๆ
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getPointReward() {
        return pointReward;
    }

    public void setPointReward(Integer pointReward) {
        this.pointReward = pointReward;
    }
}