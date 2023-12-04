package api.rest.request;

import api.domains.model.DoctorDto;
import api.domains.model.PatientDto;
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
public class ConsultationRequest {

    private Long id;
    private LocalDate consultationDate;
    private String description;
    private Status statusConsltation;
    private DoctorDto doctor;
    private PatientDto patient;
}
