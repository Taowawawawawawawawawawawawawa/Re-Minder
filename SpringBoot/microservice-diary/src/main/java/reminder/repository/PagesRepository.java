package reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.Pages;

public interface PagesRepository extends JpaRepository<Pages, Long> {
}
