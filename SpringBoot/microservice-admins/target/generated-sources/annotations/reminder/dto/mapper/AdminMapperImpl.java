package reminder.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reminder.domain.Admins;
import reminder.dto.AdminDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T19:18:53+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public void updateAdminFromDto(AdminDTO dto, Admins entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getAdminEmail() != null ) {
            entity.setAdminEmail( dto.getAdminEmail() );
        }
        if ( dto.getAdminName() != null ) {
            entity.setAdminName( dto.getAdminName() );
        }
        if ( dto.getPassword() != null ) {
            entity.setPassword( dto.getPassword() );
        }
    }

    @Override
    public void updateAdminFromEntity(Admins entity, AdminDTO dto) {
        if ( entity == null ) {
            return;
        }

        if ( entity.getAdminId() != null ) {
            dto.setAdminId( entity.getAdminId() );
        }
        if ( entity.getAdminEmail() != null ) {
            dto.setAdminEmail( entity.getAdminEmail() );
        }
        if ( entity.getAdminName() != null ) {
            dto.setAdminName( entity.getAdminName() );
        }
        if ( entity.getPassword() != null ) {
            dto.setPassword( entity.getPassword() );
        }
    }
}
