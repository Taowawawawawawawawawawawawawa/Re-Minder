package reminder.domain;

import javax.persistence.*;

@Entity
public class BerylSell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long berylId;

    private int berylAmount; // The amount of Beryl being sold

    private int pointAmount; // The amount of points equivalent to the Beryl

    private double price; // Price in baht

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

