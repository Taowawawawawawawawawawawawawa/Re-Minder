package reminder.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reminder.domain.Users;
import reminder.dto.UsersDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T19:19:04+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class UsersMapperImpl implements UsersMapper {

    @Override
    public void updateUsersFromDto(UsersDTO dto, Users entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getDateOfBirth() != null ) {
            entity.setDateOfBirth( dto.getDateOfBirth() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        if ( dto.getPassword() != null ) {
            entity.setPassword( dto.getPassword() );
        }
        if ( dto.getMbti() != null ) {
            entity.setMbti( dto.getMbti() );
        }
        entity.setBeryl( dto.getBeryl() );
        entity.setPoints( dto.getPoints() );
    }

    @Override
    public void updateUsersFromEntity(Users entity, UsersDTO dto) {
        if ( entity == null ) {
            return;
        }

        if ( entity.getUserId() != null ) {
            dto.setUserId( entity.getUserId() );
        }
        if ( entity.getName() != null ) {
            dto.setName( entity.getName() );
        }
        if ( entity.getDateOfBirth() != null ) {
            dto.setDateOfBirth( entity.getDateOfBirth() );
        }
        if ( entity.getEmail() != null ) {
            dto.setEmail( entity.getEmail() );
        }
        if ( entity.getPassword() != null ) {
            dto.setPassword( entity.getPassword() );
        }
        if ( entity.getMbti() != null ) {
            dto.setMbti( entity.getMbti() );
        }
        dto.setBeryl( entity.getBeryl() );
        dto.setPoints( entity.getPoints() );
    }
}
