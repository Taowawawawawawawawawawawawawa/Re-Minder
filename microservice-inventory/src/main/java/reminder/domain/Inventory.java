package reminder.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inventoryId;

    @Column(name = "userId", nullable = false)
    private Long userId; // Store user by ID rather than the Users entity

    @ElementCollection
    private List<Long> costumeList; // List of costume IDs owned by the user

    @ElementCollection
    private List<Long> rewardList; // List of reward IDs owned by the user

    @ElementCollection
    private List<Long> themeList; // List of theme IDs owned by the user

    public Inventory() {
    }

    public Inventory(Long inventoryId, Long userId, List<Long> costumeList, List<Long> rewardList, List<Long> themeList) {
        this.inventoryId = inventoryId;
        this.userId = userId;
        this.costumeList = costumeList;
        this.rewardList = rewardList;
        this.themeList = themeList;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getCostumeList() {
        return costumeList;
    }

    public void setCostumeList(List<Long> costumeList) {
        this.costumeList = costumeList;
    }

    public List<Long> getRewardList() {
        return rewardList;
    }

    public void setRewardList(List<Long> rewardList) {
        this.rewardList = rewardList;
    }

    public List<Long> getThemeList() {
        return themeList;
    }

    public void setThemeList(List<Long> themeList) {
        this.themeList = themeList;
    }
}
