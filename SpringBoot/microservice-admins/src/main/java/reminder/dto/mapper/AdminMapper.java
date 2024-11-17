package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import reminder.domain.Admins;
import reminder.dto.AdminDTO;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "adminId", ignore = true)
    void updateAdminFromDto(AdminDTO dto, @MappingTarget Admins entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdminFromEntity(Admins entity, @MappingTarget AdminDTO dto);
}
