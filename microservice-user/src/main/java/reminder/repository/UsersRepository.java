package reminder.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import reminder.domain.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findAll();

    Users findByEmail(String email);
}
