package reminder.dto;

import java.util.List;

public class DiaryDTO {
    private Long diaryId;
    private Long userId;
    private List<PagesDTO> stories;

    public DiaryDTO() {}

    public DiaryDTO(Long diaryId, Long userId, List<PagesDTO> stories) {
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

    public List<PagesDTO> getStories() {
        return stories;
    }

    public void setStories(List<PagesDTO> stories) {
        this.stories = stories;
    }
}
