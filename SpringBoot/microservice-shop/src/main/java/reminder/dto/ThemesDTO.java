package reminder.dto;

public class ThemesDTO {
    private Long themeId;
    private String frameSpriteArts;
    private String backGround;
    private String bgm;
    private int price;
    private String themeName;

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
    public ThemesDTO() {}

    public ThemesDTO(Long themeId, String frameSpriteArts, String backGround, String bgm, int price) {
        this.themeId = themeId;
        this.frameSpriteArts = frameSpriteArts;
        this.backGround = backGround;
        this.bgm = bgm;
        this.price = price;
    }

    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public String getFrameSpriteArts() {
        return frameSpriteArts;
    }

    public void setFrameSpriteArts(String frameSpriteArts) {
        this.frameSpriteArts = frameSpriteArts;
    }

    public String getBackGround() {
        return backGround;
    }

    public void setBackGround(String backGround) {
        this.backGround = backGround;
    }

    public String getBgm() {
        return bgm;
    }

    public void setBgm(String bgm) {
        this.bgm = bgm;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // Getters and Setters
    
}
