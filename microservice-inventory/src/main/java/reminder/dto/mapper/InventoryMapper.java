package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import reminder.domain.Inventory;
import reminder.dto.InventoryDTO;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInventoryFromDto(InventoryDTO dto, @MappingTarget Inventory entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInventoryFromEntity(Inventory entity, @MappingTarget InventoryDTO dto);
}
