package reminder.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user; // Each user has one inventory

    @ElementCollection
    private List<Long> costumeList; // List of costume IDs owned by the user

    @ElementCollection
    private List<Long> rewardList; // List of reward IDs owned by the user

    @ElementCollection
    private List<Long> themeList; // List of theme IDs owned by the user

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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

