package reminder.dto;

import java.util.List;

public class QuestDTO {
    private Long id;
    private Long questId;
    private String questName;
    private String questDescription;
    private int difficulty;
    private int berylReward;
    private int pointReward;
    private String questSubmitMethod;
    private String targetObject; // ชื่อ object ที่ต้องการให้ตรวจจับ
    private List<String> suitableMBTI; // MBTI ที่เหมาะสม
    private List<String> availableTime; // ช่วงเวลาที่ทำ Quest ได้

    // Getter และ Setter
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

    public String getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject;
    }

    public List<String> getSuitableMBTI() {
        return suitableMBTI;
    }

    public void setSuitableMBTI(List<String> suitableMBTI) {
        this.suitableMBTI = suitableMBTI;
    }

    public List<String> getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(List<String> availableTime) {
        this.availableTime = availableTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}