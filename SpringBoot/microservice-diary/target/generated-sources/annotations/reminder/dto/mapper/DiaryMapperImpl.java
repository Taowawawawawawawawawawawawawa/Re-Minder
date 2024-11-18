package reminder.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reminder.domain.Diary;
import reminder.domain.Pages;
import reminder.dto.DiaryDTO;
import reminder.dto.PagesDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T19:18:59+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class DiaryMapperImpl implements DiaryMapper {

    @Override
    public void updateDiaryFromDto(DiaryDTO dto, Diary entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getUserId() != null ) {
            entity.setUserId( dto.getUserId() );
        }
        if ( entity.getStories() != null ) {
            List<Pages> list = pagesDTOListToPagesList( dto.getStories() );
            if ( list != null ) {
                entity.getStories().clear();
                entity.getStories().addAll( list );
            }
        }
        else {
            List<Pages> list = pagesDTOListToPagesList( dto.getStories() );
            if ( list != null ) {
                entity.setStories( list );
            }
        }
    }

    @Override
    public void updateDiaryFromEntity(Diary entity, DiaryDTO dto) {
        if ( entity == null ) {
            return;
        }

        if ( entity.getDiaryId() != null ) {
            dto.setDiaryId( entity.getDiaryId() );
        }
        if ( entity.getUserId() != null ) {
            dto.setUserId( entity.getUserId() );
        }
        if ( dto.getStories() != null ) {
            List<PagesDTO> list = pagesListToPagesDTOList( entity.getStories() );
            if ( list != null ) {
                dto.getStories().clear();
                dto.getStories().addAll( list );
            }
        }
        else {
            List<PagesDTO> list = pagesListToPagesDTOList( entity.getStories() );
            if ( list != null ) {
                dto.setStories( list );
            }
        }
    }

    protected Pages pagesDTOToPages(PagesDTO pagesDTO) {
        if ( pagesDTO == null ) {
            return null;
        }

        Pages pages = new Pages();

        pages.setPageId( pagesDTO.getPageId() );
        pages.setCreateDate( pagesDTO.getCreateDate() );
        pages.setDetails( pagesDTO.getDetails() );

        return pages;
    }

    protected List<Pages> pagesDTOListToPagesList(List<PagesDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Pages> list1 = new ArrayList<Pages>( list.size() );
        for ( PagesDTO pagesDTO : list ) {
            list1.add( pagesDTOToPages( pagesDTO ) );
        }

        return list1;
    }

    protected PagesDTO pagesToPagesDTO(Pages pages) {
        if ( pages == null ) {
            return null;
        }

        PagesDTO pagesDTO = new PagesDTO();

        pagesDTO.setPageId( pages.getPageId() );
        pagesDTO.setCreateDate( pages.getCreateDate() );
        pagesDTO.setDetails( pages.getDetails() );

        return pagesDTO;
    }

    protected List<PagesDTO> pagesListToPagesDTOList(List<Pages> list) {
        if ( list == null ) {
            return null;
        }

        List<PagesDTO> list1 = new ArrayList<PagesDTO>( list.size() );
        for ( Pages pages : list ) {
            list1.add( pagesToPagesDTO( pages ) );
        }

        return list1;
    }
}
