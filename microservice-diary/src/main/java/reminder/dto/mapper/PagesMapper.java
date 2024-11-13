package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import reminder.domain.Pages;
import reminder.dto.PagesDTO;

@Mapper(componentModel = "spring")
public interface PagesMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "pageId", ignore = true)
    void updatePagesFromDto(PagesDTO dto, @MappingTarget Pages entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePagesFromEntity(Pages entity, @MappingTarget PagesDTO dto);
}
