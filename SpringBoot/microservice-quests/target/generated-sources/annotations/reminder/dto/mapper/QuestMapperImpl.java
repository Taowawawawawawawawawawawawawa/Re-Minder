package reminder.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reminder.domain.Quest;
import reminder.dto.QuestDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T14:24:45+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class QuestMapperImpl implements QuestMapper {

    @Override
    public void updateQuestFromDto(QuestDTO dto, Quest entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getQuestId() != null ) {
            entity.setQuestId( dto.getQuestId() );
        }
        if ( dto.getQuestName() != null ) {
            entity.setQuestName( dto.getQuestName() );
        }
        entity.setDifficulty( dto.getDifficulty() );
        if ( entity.getAvailableTime() != null ) {
            List<Integer> list = dto.getAvailableTime();
            if ( list != null ) {
                entity.getAvailableTime().clear();
                entity.getAvailableTime().addAll( list );
            }
        }
        else {
            List<Integer> list = dto.getAvailableTime();
            if ( list != null ) {
                entity.setAvailableTime( new ArrayList<Integer>( list ) );
            }
        }
        if ( dto.getQuestDescription() != null ) {
            entity.setQuestDescription( dto.getQuestDescription() );
        }
        if ( dto.getQuestSubmitMethod() != null ) {
            entity.setQuestSubmitMethod( dto.getQuestSubmitMethod() );
        }
        entity.setBerylReward( dto.getBerylReward() );
        entity.setPointReward( dto.getPointReward() );
    }

    @Override
    public void updateQuestFromEntity(Quest entity, QuestDTO dto) {
        if ( entity == null ) {
            return;
        }

        if ( entity.getQuestId() != null ) {
            dto.setQuestId( entity.getQuestId() );
        }
        if ( entity.getQuestName() != null ) {
            dto.setQuestName( entity.getQuestName() );
        }
        dto.setDifficulty( entity.getDifficulty() );
        if ( dto.getAvailableTime() != null ) {
            List<Integer> list = entity.getAvailableTime();
            if ( list != null ) {
                dto.getAvailableTime().clear();
                dto.getAvailableTime().addAll( list );
            }
        }
        else {
            List<Integer> list = entity.getAvailableTime();
            if ( list != null ) {
                dto.setAvailableTime( new ArrayList<Integer>( list ) );
            }
        }
        if ( entity.getQuestDescription() != null ) {
            dto.setQuestDescription( entity.getQuestDescription() );
        }
        if ( entity.getQuestSubmitMethod() != null ) {
            dto.setQuestSubmitMethod( entity.getQuestSubmitMethod() );
        }
        dto.setBerylReward( entity.getBerylReward() );
        dto.setPointReward( entity.getPointReward() );
    }
}
