package reminder.dto;

import java.time.LocalDate;

public class PagesDTO {
    private Long pageId;
    private Long diaryId;
    private LocalDate createDate;
    private String details;

    public PagesDTO() {}

    public PagesDTO(Long pageId, Long diaryId, LocalDate createDate, String details) {
        this.pageId = pageId;
        this.diaryId = diaryId;
        this.createDate = createDate;
        this.details = details;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Long diaryId) {
        this.diaryId = diaryId;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
