package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import reminder.domain.Rewards;
import reminder.dto.RewardsDTO;

@Mapper(componentModel = "spring")
public interface RewardsMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRewardsFromDto(RewardsDTO dto, @MappingTarget Rewards entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRewardsFromEntity(Rewards entity, @MappingTarget RewardsDTO dto);
}
