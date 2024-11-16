package reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.Quest;

public interface QuestRepository extends JpaRepository<Quest, Long> {
}