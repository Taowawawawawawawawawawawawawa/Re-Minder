package reminder.repository;

import reminder.domain.QuestLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestLogRepository extends JpaRepository<QuestLog, Long> {
    List<QuestLog> findByQuestId(Long questId);
}