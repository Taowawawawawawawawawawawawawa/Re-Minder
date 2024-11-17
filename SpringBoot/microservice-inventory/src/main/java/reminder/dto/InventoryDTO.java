package reminder.dto;

import java.util.List;

public class InventoryDTO {
    private Long inventoryId;
    private Long userId;
    private List<Long> costumeList;
    private List<Long> rewardList;
    private List<Long> themeList;

    public InventoryDTO() {}

    public InventoryDTO(Long inventoryId, Long userId, List<Long> costumeList, List<Long> rewardList, List<Long> themeList) {
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
