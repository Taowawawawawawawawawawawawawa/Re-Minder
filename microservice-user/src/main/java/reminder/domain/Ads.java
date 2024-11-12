package reminder.domain;

import javax.persistence.*;

@Entity
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adsId;

    private String adsName; // Name of the advertisement

    private String adsSrc; // Source URL or path for the advertisement image or video

    private String productLink; // URL link to the product or application being advertised

    public Long getAdsId() {
        return adsId;
    }

    public void setAdsId(Long adsId) {
        this.adsId = adsId;
    }

    public String getAdsName() {
        return adsName;
    }

    public void setAdsName(String adsName) {
        this.adsName = adsName;
    }

    public String getAdsSrc() {
        return adsSrc;
    }

    public void setAdsSrc(String adsSrc) {
        this.adsSrc = adsSrc;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    
}

