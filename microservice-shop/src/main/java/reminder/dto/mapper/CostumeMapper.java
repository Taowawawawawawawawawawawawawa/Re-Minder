package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import reminder.domain.Costume;
import reminder.dto.CostumeDTO;

@Mapper(componentModel = "spring")
public interface CostumeMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCostumeFromDto(CostumeDTO dto, @MappingTarget Costume entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCostumeFromEntity(Costume entity, @MappingTarget CostumeDTO dto);
}
