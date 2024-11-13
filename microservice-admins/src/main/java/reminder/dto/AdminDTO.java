package reminder.dto;

public class AdminDTO {
    private Long adminId;
    private String adminEmail;
    private String adminName;
    private String password;

    // Constructors
    public AdminDTO() {}

    public AdminDTO(Long adminId, String adminEmail, String adminName, String password) {
        this.adminId = adminId;
        this.adminEmail = adminEmail;
        this.adminName = adminName;
        this.password = password;
    }

    // Getters and Setters
    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
