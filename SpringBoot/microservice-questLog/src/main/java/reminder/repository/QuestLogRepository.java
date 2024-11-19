package reminder.repository;

import reminder.domain.QuestLog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestLogRepository extends JpaRepository<QuestLog, Long> {
    List<QuestLog> findByStatus(String status); // Custom method to find quest logs by status
}