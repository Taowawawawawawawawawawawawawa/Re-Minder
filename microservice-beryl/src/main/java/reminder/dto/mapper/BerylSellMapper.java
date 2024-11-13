package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import reminder.domain.BerylSell;
import reminder.dto.BerylSellDTO;

@Mapper(componentModel = "spring")
public interface BerylSellMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBerylSellFromDto(BerylSellDTO dto, @MappingTarget BerylSell entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBerylSellFromEntity(BerylSell entity, @MappingTarget BerylSellDTO dto);
}
