// CostumeMapper.java
package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import reminder.domain.Costume;
import reminder.dto.CostumeDTO;

@Mapper(componentModel = "spring")
public interface CostumeMapper {

    void updateCostumeFromDto(CostumeDTO dto, @MappingTarget Costume entity);

    void updateCostumeFromEntity(Costume entity, @MappingTarget CostumeDTO dto);
}
