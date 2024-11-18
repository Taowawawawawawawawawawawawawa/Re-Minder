package reminder.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reminder.domain.Rewards;
import reminder.dto.RewardsDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T19:19:03+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class RewardsMapperImpl implements RewardsMapper {

    @Override
    public void updateRewardsFromDto(RewardsDTO dto, Rewards entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getRewardId() != null ) {
            entity.setRewardId( dto.getRewardId() );
        }
        if ( dto.getRewardSpriteArts() != null ) {
            entity.setRewardSpriteArts( dto.getRewardSpriteArts() );
        }
        if ( dto.getRewardName() != null ) {
            entity.setRewardName( dto.getRewardName() );
        }
        if ( dto.getRewardDescription() != null ) {
            entity.setRewardDescription( dto.getRewardDescription() );
        }
        entity.setRewardPrice( dto.getRewardPrice() );
    }

    @Override
    public void updateRewardsFromEntity(Rewards entity, RewardsDTO dto) {
        if ( entity == null ) {
            return;
        }

        if ( entity.getRewardId() != null ) {
            dto.setRewardId( entity.getRewardId() );
        }
        if ( entity.getRewardSpriteArts() != null ) {
            dto.setRewardSpriteArts( entity.getRewardSpriteArts() );
        }
        if ( entity.getRewardName() != null ) {
            dto.setRewardName( entity.getRewardName() );
        }
        if ( entity.getRewardDescription() != null ) {
            dto.setRewardDescription( entity.getRewardDescription() );
        }
        dto.setRewardPrice( entity.getRewardPrice() );
    }
}
