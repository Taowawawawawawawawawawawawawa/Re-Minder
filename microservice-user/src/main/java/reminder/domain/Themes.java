package reminder.domain;

import javax.persistence.*;

@Entity
public class Themes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long themeId;

    private String frameSpriteArts; // Path to the UI frame image(s) file(s)

    private String backGround; // Path to the background image file

    private String bgm; // Path to the background music (BGM) sound file

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

    
}

