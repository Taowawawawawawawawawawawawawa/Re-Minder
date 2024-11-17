package reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.Diary;
import java.util.Optional;


public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Optional<Diary> findByUserId(Long userId);

}
