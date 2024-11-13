package reminder.dto;

public class BerylSellDTO {
    private Long berylId;
    private int berylAmount;
    private int pointAmount;
    private double price;

    public BerylSellDTO() {}

    public BerylSellDTO(Long berylId, int berylAmount, int pointAmount, double price) {
        this.berylId = berylId;
        this.berylAmount = berylAmount;
        this.pointAmount = pointAmount;
        this.price = price;
    }

    public Long getBerylId() {
        return berylId;
    }

    public void setBerylId(Long berylId) {
        this.berylId = berylId;
    }

    public int getBerylAmount() {
        return berylAmount;
    }

    public void setBerylAmount(int berylAmount) {
        this.berylAmount = berylAmount;
    }

    public int getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(int pointAmount) {
        this.pointAmount = pointAmount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
