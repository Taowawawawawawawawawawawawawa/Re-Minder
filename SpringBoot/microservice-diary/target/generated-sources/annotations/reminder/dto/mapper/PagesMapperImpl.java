package reminder.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reminder.domain.Pages;
import reminder.dto.PagesDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T19:19:00+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class PagesMapperImpl implements PagesMapper {

    @Override
    public void updatePagesFromDto(PagesDTO dto, Pages entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getCreateDate() != null ) {
            entity.setCreateDate( dto.getCreateDate() );
        }
        if ( dto.getDetails() != null ) {
            entity.setDetails( dto.getDetails() );
        }
    }

    @Override
    public void updatePagesFromEntity(Pages entity, PagesDTO dto) {
        if ( entity == null ) {
            return;
        }

        if ( entity.getPageId() != null ) {
            dto.setPageId( entity.getPageId() );
        }
        if ( entity.getCreateDate() != null ) {
            dto.setCreateDate( entity.getCreateDate() );
        }
        if ( entity.getDetails() != null ) {
            dto.setDetails( entity.getDetails() );
        }
    }
}
