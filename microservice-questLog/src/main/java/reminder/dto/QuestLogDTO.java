package reminder.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class QuestLogDTO {

    private Long questLogId;
    private Long questId;
    private Long userId;
    private String attachmentTxt;
    private byte[] attachmentPic;
    private LocalDate submitDate;
    private LocalTime submitTime;
    private String status;
    private String message;

    public QuestLogDTO() {}

    public QuestLogDTO(Long questLogId, Long questId, Long userId, String attachmentTxt, byte[] attachmentPic, LocalDate submitDate, LocalTime submitTime, String status, String message) {
        this.questLogId = questLogId;
        this.questId = questId;
        this.userId = userId;
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

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
