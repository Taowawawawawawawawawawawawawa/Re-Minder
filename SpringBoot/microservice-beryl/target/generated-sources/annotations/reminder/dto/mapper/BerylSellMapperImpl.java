package reminder.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reminder.domain.BerylSell;
import reminder.dto.BerylSellDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T19:18:57+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class BerylSellMapperImpl implements BerylSellMapper {

    @Override
    public void updateBerylSellFromDto(BerylSellDTO dto, BerylSell entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getBerylId() != null ) {
            entity.setBerylId( dto.getBerylId() );
        }
        entity.setBerylAmount( dto.getBerylAmount() );
        entity.setPointAmount( dto.getPointAmount() );
        entity.setPrice( dto.getPrice() );
    }

    @Override
    public void updateBerylSellFromEntity(BerylSell entity, BerylSellDTO dto) {
        if ( entity == null ) {
            return;
        }

        if ( entity.getBerylId() != null ) {
            dto.setBerylId( entity.getBerylId() );
        }
        dto.setBerylAmount( entity.getBerylAmount() );
        dto.setPointAmount( entity.getPointAmount() );
        dto.setPrice( entity.getPrice() );
    }
}
