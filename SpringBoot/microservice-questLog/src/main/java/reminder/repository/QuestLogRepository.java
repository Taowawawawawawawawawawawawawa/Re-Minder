package reminder.repository;

import reminder.domain.QuestLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestLogRepository extends JpaRepository<QuestLog, Long> {
    // สามารถเพิ่ม query ที่ต้องการที่นี่ได้
}