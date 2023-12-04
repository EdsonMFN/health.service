package api.domains.repository;

import api.domains.entity.Clinic;
import api.domains.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
