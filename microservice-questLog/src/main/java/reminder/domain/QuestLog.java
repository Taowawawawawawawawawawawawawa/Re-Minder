package reminder.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuestLog {

 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // เพื่อให้ JPA สร้าง ID อัตโนมัติ
    private Long id;
    private Long questId;
    private String questName;
    private String questDescription;
    private String status;  // เก็บสถานะของเควส

    // ฟิลด์เพิ่มเติม
    private Integer berylReward;    
    private String difficulty;
    private Integer pointReward;

    // Getters and Setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBerylReward() {
        return berylReward;
    }

    public void setBerylReward(Integer berylReward) {
        this.berylReward = berylReward;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getPointReward() {
        return pointReward;
    }

    public void setPointReward(Integer pointReward) {
        this.pointReward = pointReward;
    }
}