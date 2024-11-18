package reminder.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reminder.domain.Ads;
import reminder.dto.AdsDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T19:18:55+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class AdsMapperImpl implements AdsMapper {

    @Override
    public void updateAdsFromDto(AdsDTO dto, Ads entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getAdsId() != null ) {
            entity.setAdsId( dto.getAdsId() );
        }
        if ( dto.getAdsName() != null ) {
            entity.setAdsName( dto.getAdsName() );
        }
        if ( dto.getAdsSrc() != null ) {
            entity.setAdsSrc( dto.getAdsSrc() );
        }
        if ( dto.getProductLink() != null ) {
            entity.setProductLink( dto.getProductLink() );
        }
    }

    @Override
    public void updateAdsFromEntity(Ads entity, AdsDTO dto) {
        if ( entity == null ) {
            return;
        }

        if ( entity.getAdsId() != null ) {
            dto.setAdsId( entity.getAdsId() );
        }
        if ( entity.getAdsName() != null ) {
            dto.setAdsName( entity.getAdsName() );
        }
        if ( entity.getAdsSrc() != null ) {
            dto.setAdsSrc( entity.getAdsSrc() );
        }
        if ( entity.getProductLink() != null ) {
            dto.setProductLink( entity.getProductLink() );
        }
    }
}
