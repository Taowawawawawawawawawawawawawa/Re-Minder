package reminder.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reminder.domain.Costume;
import reminder.dto.CostumeDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T19:19:03+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class CostumeMapperImpl implements CostumeMapper {

    @Override
    public void updateCostumeFromDto(CostumeDTO dto, Costume entity) {
        if ( dto == null ) {
            return;
        }

        entity.setCostumeId( dto.getCostumeId() );
        if ( dto.getCostumeType() != null ) {
            entity.setCostumeType( Enum.valueOf( Costume.CostumeType.class, dto.getCostumeType() ) );
        }
        else {
            entity.setCostumeType( null );
        }
        entity.setCostumeName( dto.getCostumeName() );
        entity.setCostumeDescription( dto.getCostumeDescription() );
        entity.setCostumeFiles( dto.getCostumeFiles() );
        entity.setPrice( dto.getPrice() );
    }

    @Override
    public void updateCostumeFromEntity(Costume entity, CostumeDTO dto) {
        if ( entity == null ) {
            return;
        }

        dto.setCostumeId( entity.getCostumeId() );
        if ( entity.getCostumeType() != null ) {
            dto.setCostumeType( entity.getCostumeType().name() );
        }
        else {
            dto.setCostumeType( null );
        }
        dto.setCostumeName( entity.getCostumeName() );
        dto.setCostumeDescription( entity.getCostumeDescription() );
        dto.setCostumeFiles( entity.getCostumeFiles() );
        dto.setPrice( entity.getPrice() );
    }
}
