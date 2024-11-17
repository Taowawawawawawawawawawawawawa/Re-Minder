package reminder.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pageId;

    @ManyToOne
    @JoinColumn(name = "diaryId", nullable = false)
    private Diary diary; // Reference to the diary this page belongs to

    private LocalDate createDate; // Date the page was created

    @Lob
    private String details; // Content written by the user on this page

    public Pages() {}

    public Pages(Long pageId, Diary diary, LocalDate createDate, String details) {
        this.pageId = pageId;
        this.diary = diary;
        this.createDate = createDate;
        this.details = details;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
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
