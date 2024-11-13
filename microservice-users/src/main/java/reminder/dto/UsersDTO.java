package reminder.dto;

import java.time.LocalDate;

import javax.persistence.Column;

public class UsersDTO {

    private Long userId;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private String mbti;
    private int beryl;
    private int points;

    public UsersDTO() {
    }

    public UsersDTO(Long userId, String name, LocalDate dateOfBirth, String email, String password, String mbti, int beryl, int points) {
        this.userId = userId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.mbti = mbti;
        this.beryl = beryl;
        this.points = points;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public int getBeryl() {
        return beryl;
    }

    public void setBeryl(int beryl) {
        this.beryl = beryl;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
