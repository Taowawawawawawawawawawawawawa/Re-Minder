package reminder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.Quest;

public interface QuestRepository extends JpaRepository<Quest, Long> {
    Optional<Quest> findById(Long id); // Fetches all fields including targetObject
}