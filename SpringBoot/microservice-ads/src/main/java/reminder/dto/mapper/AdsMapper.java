package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import reminder.domain.Ads;
import reminder.dto.AdsDTO;

@Mapper(componentModel = "spring")
public interface AdsMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdsFromDto(AdsDTO dto, @MappingTarget Ads entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdsFromEntity(Ads entity, @MappingTarget AdsDTO dto);
}
