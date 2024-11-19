package reminder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.Admins;

public interface AdminRepository extends JpaRepository<Admins, Long> {
    List<Admins> findAll();
    
    Admins findByAdminEmail(String adminEmail);

}
