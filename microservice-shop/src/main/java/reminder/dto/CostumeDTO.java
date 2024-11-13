package reminder.dto;

public class CostumeDTO {
    private Long costumeId;
    private String costumeType;
    private String costumeName;
    private String costumeDescription;
    private String costumeFiles;
    private int price;

    public CostumeDTO() {}

    public CostumeDTO(Long costumeId, String costumeType, String costumeName, String costumeDescription, String costumeFiles, int price) {
        this.costumeId = costumeId;
        this.costumeType = costumeType;
        this.costumeName = costumeName;
        this.costumeDescription = costumeDescription;
        this.costumeFiles = costumeFiles;
        this.price = price;
    }

    public Long getCostumeId() {
        return costumeId;
    }

    public void setCostumeId(Long costumeId) {
        this.costumeId = costumeId;
    }

    public String getCostumeType() {
        return costumeType;
    }

    public void setCostumeType(String costumeType) {
        this.costumeType = costumeType;
    }

    public String getCostumeName() {
        return costumeName;
    }

    public void setCostumeName(String costumeName) {
        this.costumeName = costumeName;
    }

    public String getCostumeDescription() {
        return costumeDescription;
    }

    public void setCostumeDescription(String costumeDescription) {
        this.costumeDescription = costumeDescription;
    }

    public String getCostumeFiles() {
        return costumeFiles;
    }

    public void setCostumeFiles(String costumeFiles) {
        this.costumeFiles = costumeFiles;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // Getters and Setters
    
}
