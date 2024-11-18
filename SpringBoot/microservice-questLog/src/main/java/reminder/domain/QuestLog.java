package reminder.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long questId;
    private Long userId;
    private String imageUrl;
    private String status; // Pending, Approved, Rejected

    // ฟิลด์ที่เพิ่มเข้าไปใน QuestLog
    private String questName;
    private String questDescription;
    private int berylReward;
    private int difficulty; // เปลี่ยนเป็น int
    private int pointReward;

    // Getter/Setter methods สำหรับฟิลด์ใหม่
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getBerylReward() {
        return berylReward;
    }

    public void setBerylReward(int berylReward) {
        this.berylReward = berylReward;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getPointReward() {
        return pointReward;
    }

    public void setPointReward(int pointReward) {
        this.pointReward = pointReward;
    }
}