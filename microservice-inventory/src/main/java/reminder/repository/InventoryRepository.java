package reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
