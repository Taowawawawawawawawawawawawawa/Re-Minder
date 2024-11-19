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

    private String targetObject; // ชื่อ object ที่ต้องการให้ตรวจจับ เช่น "dog"

    // Getter และ Setter
    public String getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject;
    }

    @ElementCollection
    @CollectionTable(name = "Quest_suitableMBTI", joinColumns = @JoinColumn(name = "Quest_questId"))
    @Column(name = "suitableMBTI")
    private List<String> suitableMBTI = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "Quest_availableTime", joinColumns = @JoinColumn(name = "Quest_questId"))
    @Column(name = "availableTime")
    private List<String> availableTime = new ArrayList<>();

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

    public List<String> getSuitableMBTI() {
        return suitableMBTI;
    }

    public void setSuitableMBTI(List<String> suitableMBTI) {
        this.suitableMBTI = suitableMBTI;
    }
}
