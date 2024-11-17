package reminder.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long diaryId;

    @Column(nullable = false)
    private Long userId; // Store user ID instead of the full Users object

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pages> stories = new ArrayList<>(); // List of pages (stories) in the diary

    public Diary() {}

    public Diary(Long diaryId, Long userId, List<Pages> stories) {
        this.diaryId = diaryId;
        this.userId = userId;
        this.stories = stories;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Long diaryId) {
        this.diaryId = diaryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Pages> getStories() {
        return stories;
    }

    public void setStories(List<Pages> stories) {
        this.stories = stories;
    }

    public void addStory(Pages page) {
        this.stories.add(page);
        page.setDiary(this);
    }

    public void removeStory(Pages page) {
        this.stories.remove(page);
        page.setDiary(null);
    }
}
