package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import reminder.domain.Quest;
import reminder.dto.QuestDTO;

@Mapper(componentModel = "spring")
public interface QuestMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateQuestFromDto(QuestDTO dto, @MappingTarget Quest entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateQuestFromEntity(Quest entity, @MappingTarget QuestDTO dto);
}
