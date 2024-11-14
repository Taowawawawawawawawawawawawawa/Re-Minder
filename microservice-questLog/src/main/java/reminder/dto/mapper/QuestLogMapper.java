package reminder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import reminder.domain.QuestLog;
import reminder.dto.QueslogtDTO;

@Mapper(componentModel = "spring")
public interface QuestLogMapper {

    @Mapping(target = "questName", source = "quest.questName")
    @Mapping(target = "questDescription", source = "quest.questDescription")
    @Mapping(target = "berylReward", source = "quest.berylReward")
    @Mapping(target = "difficulty", source = "quest.difficulty")
    @Mapping(target = "pointReward", source = "quest.pointReward")
    QueslogtDTO questToQuestLogDTO(QuestLog quest);

    @Mapping(target = "questId", source = "questLogDTO.questId")
    @Mapping(target = "questName", source = "questLogDTO.questName")
    @Mapping(target = "questDescription", source = "questLogDTO.questDescription")
    @Mapping(target = "berylReward", source = "questLogDTO.berylReward")
    @Mapping(target = "difficulty", source = "questLogDTO.difficulty")
    @Mapping(target = "pointReward", source = "questLogDTO.pointReward")
    QuestLog questLogDTOToQuest(QueslogtDTO questLogDTO);
}