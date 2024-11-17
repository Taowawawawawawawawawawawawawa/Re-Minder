// CostumeRepository.java
package reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.Costume;

public interface CostumeRepository extends JpaRepository<Costume, Long> {
}
