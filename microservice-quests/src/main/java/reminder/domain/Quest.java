package reminder.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questId;

    private String questName;

    private int difficulty;

    @ElementCollection
    @CollectionTable(name = "quest_available_times", joinColumns = @JoinColumn(name = "quest_id"))
    @Column(name = "available_time")
    private List<Integer> availableTime;

    private String questDescription;

    private String questSubmitMethod;

    private int berylReward;

    private int pointReward;

    public Quest() {}

    public Quest(String questName, int difficulty, List<Integer> availableTime, String questDescription, String questSubmitMethod, int berylReward, int pointReward) {
        this.questName = questName;
        this.difficulty = difficulty;
        this.availableTime = availableTime;
        this.questDescription = questDescription;
        this.questSubmitMethod = questSubmitMethod;
        this.berylReward = berylReward;
        this.pointReward = pointReward;
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public List<Integer> getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(List<Integer> availableTime) {
        this.availableTime = availableTime;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public void setQuestDescription(String questDescription) {
        this.questDescription = questDescription;
    }

    public String getQuestSubmitMethod() {
        return questSubmitMethod;
    }

    public void setQuestSubmitMethod(String questSubmitMethod) {
        this.questSubmitMethod = questSubmitMethod;
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
}
