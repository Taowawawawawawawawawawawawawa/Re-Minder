package reminder.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reminder.domain.Themes;
import reminder.dto.ThemesDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T19:19:03+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class ThemesMapperImpl implements ThemesMapper {

    @Override
    public void updateThemesFromDto(ThemesDTO dto, Themes entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getThemeId() != null ) {
            entity.setThemeId( dto.getThemeId() );
        }
        if ( dto.getFrameSpriteArts() != null ) {
            entity.setFrameSpriteArts( dto.getFrameSpriteArts() );
        }
        if ( dto.getBackGround() != null ) {
            entity.setBackGround( dto.getBackGround() );
        }
        if ( dto.getBgm() != null ) {
            entity.setBgm( dto.getBgm() );
        }
        entity.setPrice( dto.getPrice() );
    }

    @Override
    public void updateThemesFromEntity(Themes entity, ThemesDTO dto) {
        if ( entity == null ) {
            return;
        }

        if ( entity.getThemeId() != null ) {
            dto.setThemeId( entity.getThemeId() );
        }
        if ( entity.getFrameSpriteArts() != null ) {
            dto.setFrameSpriteArts( entity.getFrameSpriteArts() );
        }
        if ( entity.getBackGround() != null ) {
            dto.setBackGround( entity.getBackGround() );
        }
        if ( entity.getBgm() != null ) {
            dto.setBgm( entity.getBgm() );
        }
        dto.setPrice( entity.getPrice() );
    }
}
