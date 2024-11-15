package reminder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByUserId(Long userId);
}
