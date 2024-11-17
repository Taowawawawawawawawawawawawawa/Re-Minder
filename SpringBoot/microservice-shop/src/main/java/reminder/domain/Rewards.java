package reminder.domain;

import javax.persistence.*;

@Entity
public class Rewards {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rewardId;

    private String rewardSpriteArts; // Path to the sprite/image file for the reward

    private String rewardName;

    private String rewardDescription;

    private int rewardPrice; // Price in points

    public Rewards(Long rewardId, String rewardSpriteArts, String rewardName, String rewardDescription,
            int rewardPrice) {
        this.rewardId = rewardId;
        this.rewardSpriteArts = rewardSpriteArts;
        this.rewardName = rewardName;
        this.rewardDescription = rewardDescription;
        this.rewardPrice = rewardPrice;
    }

    public Rewards() {
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public String getRewardSpriteArts() {
        return rewardSpriteArts;
    }

    public void setRewardSpriteArts(String rewardSpriteArts) {
        this.rewardSpriteArts = rewardSpriteArts;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getRewardDescription() {
        return rewardDescription;
    }

    public void setRewardDescription(String rewardDescription) {
        this.rewardDescription = rewardDescription;
    }

    public int getRewardPrice() {
        return rewardPrice;
    }

    public void setRewardPrice(int rewardPrice) {
        this.rewardPrice = rewardPrice;
    }

    
}
