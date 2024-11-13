package reminder.dto;

public class AdsDTO {
    private Long adsId;
    private String adsName;
    private String adsSrc;
    private String productLink;

    public AdsDTO() {}

    public AdsDTO(Long adsId, String adsName, String adsSrc, String productLink) {
        this.adsId = adsId;
        this.adsName = adsName;
        this.adsSrc = adsSrc;
        this.productLink = productLink;
    }

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
