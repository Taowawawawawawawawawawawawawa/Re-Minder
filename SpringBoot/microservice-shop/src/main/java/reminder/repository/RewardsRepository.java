package reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.Rewards;

public interface RewardsRepository extends JpaRepository<Rewards, Long> {
}
