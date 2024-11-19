package reminder.controller;

public class LoginResponse {
    private Long id;
    private String role;
    private String name;

    // Constructor
    public LoginResponse(Long id, String role, String name) {
        this.id = id;
        this.role = role;
        this.name = name;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
