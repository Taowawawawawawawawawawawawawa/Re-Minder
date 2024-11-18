package reminder.domain;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // ใช้ AUTO_INCREMENT หรือ IDENTITY
    private Long questId;

    private String questName;
    private String questDescription;
    private int difficulty;
    private int berylReward;
    private int pointReward;
    private String questSubmitMethod;

    @ElementCollection
    private List<String> availableTime;

    @PrePersist
    public void prePersist() {
        if (availableTime == null) {
            availableTime = new ArrayList<>();
        }
    }


    // Getter and Setter methods
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

    public List<String> getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(List<String> availableTime) {
        this.availableTime = availableTime;
    }
}