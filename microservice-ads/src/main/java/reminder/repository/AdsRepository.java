package reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.Ads;

public interface AdsRepository extends JpaRepository<Ads, Long> {
}
