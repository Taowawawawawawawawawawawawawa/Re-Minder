package reminder.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // เปลี่ยนเป็น GenerationType.IDENTITY
    private Long questId;

    private String questName;
    private String questDescription;
    private int difficulty;
    private int berylReward;
    private int pointReward;
    private String questSubmitMethod; // เช่น text หรือ image

    @ElementCollection
    @CollectionTable(name = "quest_available_times", joinColumns = @JoinColumn(name = "quest_id"))
    @Column(name = "available_time")
    private List<Integer> availableTime; // เวลาในการทำเควส

    // Default constructor required for Hibernate
    public Quest() {}

    // Constructor และ Getter/Setter ทั้งหมด

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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getBerylReward() {
        return berylReward;
    }

    public void setBerylReward(int berylReward) {
        this.berylReward = berylReward;
    }

    public int getPointReward() {
        return pointReward;
    }

    public void setPointReward(int pointReward) {
        this.pointReward = pointReward;
    }

    public String getQuestSubmitMethod() {
        return questSubmitMethod;
    }

    public void setQuestSubmitMethod(String questSubmitMethod) {
        this.questSubmitMethod = questSubmitMethod;
    }

    public List<Integer> getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(List<Integer> availableTime) {
        this.availableTime = availableTime;
    }
}