package reminder.dto;

import java.util.List;

public class QuestDTO {

    private Long questId;
    private String questName;
    private String questDescription;
    private int difficulty;
    private int berylReward;
    private int pointReward;
    private String questSubmitMethod;
    private List<String> availableTime;

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