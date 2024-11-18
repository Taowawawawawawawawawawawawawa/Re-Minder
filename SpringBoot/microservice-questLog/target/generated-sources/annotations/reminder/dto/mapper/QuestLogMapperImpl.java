package reminder.dto.mapper;

import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reminder.domain.QuestLog;
import reminder.dto.QuestLogDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T14:24:44+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class QuestLogMapperImpl implements QuestLogMapper {

    @Override
    public void updateQuestLogFromDto(QuestLogDTO dto, QuestLog entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getQuestId() != null ) {
            entity.setQuestId( dto.getQuestId() );
        }
        if ( dto.getUserId() != null ) {
            entity.setUserId( dto.getUserId() );
        }
        if ( dto.getAttachmentTxt() != null ) {
            entity.setAttachmentTxt( dto.getAttachmentTxt() );
        }
        byte[] attachmentPic = dto.getAttachmentPic();
        if ( attachmentPic != null ) {
            entity.setAttachmentPic( Arrays.copyOf( attachmentPic, attachmentPic.length ) );
        }
        if ( dto.getSubmitDate() != null ) {
            entity.setSubmitDate( dto.getSubmitDate() );
        }
        if ( dto.getSubmitTime() != null ) {
            entity.setSubmitTime( dto.getSubmitTime() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( Enum.valueOf( QuestLog.Status.class, dto.getStatus() ) );
        }
        if ( dto.getMessage() != null ) {
            entity.setMessage( dto.getMessage() );
        }
    }

    @Override
    public void updateQuestLogFromEntity(QuestLog entity, QuestLogDTO dto) {
        if ( entity == null ) {
            return;
        }

        if ( entity.getQuestLogId() != null ) {
            dto.setQuestLogId( entity.getQuestLogId() );
        }
        if ( entity.getQuestId() != null ) {
            dto.setQuestId( entity.getQuestId() );
        }
        if ( entity.getUserId() != null ) {
            dto.setUserId( entity.getUserId() );
        }
        if ( entity.getAttachmentTxt() != null ) {
            dto.setAttachmentTxt( entity.getAttachmentTxt() );
        }
        byte[] attachmentPic = entity.getAttachmentPic();
        if ( attachmentPic != null ) {
            dto.setAttachmentPic( Arrays.copyOf( attachmentPic, attachmentPic.length ) );
        }
        if ( entity.getSubmitDate() != null ) {
            dto.setSubmitDate( entity.getSubmitDate() );
        }
        if ( entity.getSubmitTime() != null ) {
            dto.setSubmitTime( entity.getSubmitTime() );
        }
        if ( entity.getStatus() != null ) {
            dto.setStatus( entity.getStatus().name() );
        }
        if ( entity.getMessage() != null ) {
            dto.setMessage( entity.getMessage() );
        }
    }
}
