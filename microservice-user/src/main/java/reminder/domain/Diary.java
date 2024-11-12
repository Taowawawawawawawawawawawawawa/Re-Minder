package reminder.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user; // Owner of the diary, each user can have only one diary

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pages> stories = new ArrayList<>(); // List of pages (stories) in the diary

    public Long getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Long diaryId) {
        this.diaryId = diaryId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Pages> getStories() {
        return stories;
    }

    public void setStories(List<Pages> stories) {
        this.stories = stories;
    }

    public void addStories(Pages page) {
        this.stories.add(page);
    }
}
