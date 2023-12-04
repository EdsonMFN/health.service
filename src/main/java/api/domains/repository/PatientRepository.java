package api.domains.repository;

import api.domains.entity.Clinic;
import api.domains.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
