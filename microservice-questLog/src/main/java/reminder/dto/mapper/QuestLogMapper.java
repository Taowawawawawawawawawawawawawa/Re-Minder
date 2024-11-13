package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import reminder.domain.QuestLog;
import reminder.dto.QuestLogDTO;

@Mapper(componentModel = "spring")
public interface QuestLogMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "questLogId", ignore = true)
    void updateQuestLogFromDto(QuestLogDTO dto, @MappingTarget QuestLog entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateQuestLogFromEntity(QuestLog entity, @MappingTarget QuestLogDTO dto);
}
