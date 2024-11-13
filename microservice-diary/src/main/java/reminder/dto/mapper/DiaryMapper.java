package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import reminder.domain.Diary;
import reminder.dto.DiaryDTO;

@Mapper(componentModel = "spring")
public interface DiaryMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "diaryId", ignore = true)
    void updateDiaryFromDto(DiaryDTO dto, @MappingTarget Diary entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDiaryFromEntity(Diary entity, @MappingTarget DiaryDTO dto);
}
