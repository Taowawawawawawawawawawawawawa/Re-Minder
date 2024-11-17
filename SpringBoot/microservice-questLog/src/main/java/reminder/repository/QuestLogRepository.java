package reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.QuestLog;

public interface QuestLogRepository extends JpaRepository<QuestLog, Long> {
}
