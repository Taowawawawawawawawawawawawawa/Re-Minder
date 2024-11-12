package reminder.domain;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class QuestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questLogId;

    @ManyToOne
    @JoinColumn(name = "quest_id", nullable = false)
    private Quest quest;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Lob
    private String attachmentTxt;

    @Lob
    private byte[] attachmentPic;

    private LocalDate submitDate;

    private LocalTime submitTime;

    @Enumerated(EnumType.STRING)
    private Status status;
    public enum Status {
        PENDING,
        PASS,
        FAIL
    }

    private String message;

    // Constructors
    public QuestLog() {}

    public QuestLog(Quest quest, Users user, String attachmentTxt, byte[] attachmentPic, LocalDate submitDate, LocalTime submitTime, Status status, String message) {
        this.quest = quest;
        this.user = user;
        this.attachmentTxt = attachmentTxt;
        this.attachmentPic = attachmentPic;
        this.submitDate = submitDate;
        this.submitTime = submitTime;
        this.status = status;
        this.message = message;
    }

    public Long getQuestLogId() {
        return questLogId;
    }

    public void setQuestLogId(Long questLogId) {
        this.questLogId = questLogId;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getAttachmentTxt() {
        return attachmentTxt;
    }

    public void setAttachmentTxt(String attachmentTxt) {
        this.attachmentTxt = attachmentTxt;
    }

    public byte[] getAttachmentPic() {
        return attachmentPic;
    }

    public void setAttachmentPic(byte[] attachmentPic) {
        this.attachmentPic = attachmentPic;
    }

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }

    public LocalTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalTime submitTime) {
        this.submitTime = submitTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
