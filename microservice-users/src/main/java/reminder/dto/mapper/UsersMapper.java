package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import reminder.domain.Users;
import reminder.dto.UsersDTO;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "userId", ignore = true)
    void updateUsersFromDto(UsersDTO dto, @MappingTarget Users entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUsersFromEntity(Users entity, @MappingTarget UsersDTO dto);
}
