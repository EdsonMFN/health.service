package api.domains.repository;

import api.domains.entity.Clinic;
import api.domains.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
