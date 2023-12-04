package api.domains.model;

import api.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDto {

    private Long id;
    private LocalDate consultationDate;
    private String description;
    private Status statusConsltation;
    private DoctorDto doctors;
    private PatientDto patients;
}
