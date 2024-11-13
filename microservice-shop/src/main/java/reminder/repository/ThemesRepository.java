package reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.Themes;

public interface ThemesRepository extends JpaRepository<Themes, Long> {
}
