package reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
