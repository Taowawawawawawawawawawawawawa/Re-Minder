package reminder.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reminder.domain.Inventory;
import reminder.dto.InventoryDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T19:19:01+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class InventoryMapperImpl implements InventoryMapper {

    @Override
    public void updateInventoryFromDto(InventoryDTO dto, Inventory entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getInventoryId() != null ) {
            entity.setInventoryId( dto.getInventoryId() );
        }
        if ( dto.getUserId() != null ) {
            entity.setUserId( dto.getUserId() );
        }
        if ( entity.getCostumeList() != null ) {
            List<Long> list = dto.getCostumeList();
            if ( list != null ) {
                entity.getCostumeList().clear();
                entity.getCostumeList().addAll( list );
            }
        }
        else {
            List<Long> list = dto.getCostumeList();
            if ( list != null ) {
                entity.setCostumeList( new ArrayList<Long>( list ) );
            }
        }
        if ( entity.getRewardList() != null ) {
            List<Long> list1 = dto.getRewardList();
            if ( list1 != null ) {
                entity.getRewardList().clear();
                entity.getRewardList().addAll( list1 );
            }
        }
        else {
            List<Long> list1 = dto.getRewardList();
            if ( list1 != null ) {
                entity.setRewardList( new ArrayList<Long>( list1 ) );
            }
        }
        if ( entity.getThemeList() != null ) {
            List<Long> list2 = dto.getThemeList();
            if ( list2 != null ) {
                entity.getThemeList().clear();
                entity.getThemeList().addAll( list2 );
            }
        }
        else {
            List<Long> list2 = dto.getThemeList();
            if ( list2 != null ) {
                entity.setThemeList( new ArrayList<Long>( list2 ) );
            }
        }
    }

    @Override
    public void updateInventoryFromEntity(Inventory entity, InventoryDTO dto) {
        if ( entity == null ) {
            return;
        }

        if ( entity.getInventoryId() != null ) {
            dto.setInventoryId( entity.getInventoryId() );
        }
        if ( entity.getUserId() != null ) {
            dto.setUserId( entity.getUserId() );
        }
        if ( dto.getCostumeList() != null ) {
            List<Long> list = entity.getCostumeList();
            if ( list != null ) {
                dto.getCostumeList().clear();
                dto.getCostumeList().addAll( list );
            }
        }
        else {
            List<Long> list = entity.getCostumeList();
            if ( list != null ) {
                dto.setCostumeList( new ArrayList<Long>( list ) );
            }
        }
        if ( dto.getRewardList() != null ) {
            List<Long> list1 = entity.getRewardList();
            if ( list1 != null ) {
                dto.getRewardList().clear();
                dto.getRewardList().addAll( list1 );
            }
        }
        else {
            List<Long> list1 = entity.getRewardList();
            if ( list1 != null ) {
                dto.setRewardList( new ArrayList<Long>( list1 ) );
            }
        }
        if ( dto.getThemeList() != null ) {
            List<Long> list2 = entity.getThemeList();
            if ( list2 != null ) {
                dto.getThemeList().clear();
                dto.getThemeList().addAll( list2 );
            }
        }
        else {
            List<Long> list2 = entity.getThemeList();
            if ( list2 != null ) {
                dto.setThemeList( new ArrayList<Long>( list2 ) );
            }
        }
    }
}
