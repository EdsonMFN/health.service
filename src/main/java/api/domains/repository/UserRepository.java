package api.domains.repository;

import api.domains.entity.Clinic;
import api.domains.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
