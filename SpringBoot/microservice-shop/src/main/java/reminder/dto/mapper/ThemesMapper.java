package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import reminder.domain.Themes;
import reminder.dto.ThemesDTO;

@Mapper(componentModel = "spring")
public interface ThemesMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateThemesFromDto(ThemesDTO dto, @MappingTarget Themes entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateThemesFromEntity(Themes entity, @MappingTarget ThemesDTO dto);
}
