package reminder.domain;

import javax.persistence.*;

@Entity
public class Costume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long costumeId;

    @Enumerated(EnumType.STRING)
    private CostumeType costumeType;
    public enum CostumeType {
        HAT,
        DRESS
    }

    private String costumeName;

    private String costumeDescription;

    private String costumeFiles; // Path to the file storage location

    private int price; // Price in Beryl

    public Long getCostumeId() {
        return costumeId;
    }

    public void setCostumeId(Long costumeId) {
        this.costumeId = costumeId;
    }

    public CostumeType getCostumeType() {
        return costumeType;
    }

    public void setCostumeType(CostumeType costumeType) {
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

}