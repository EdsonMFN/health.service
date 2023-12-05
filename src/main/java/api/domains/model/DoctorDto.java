package api.domains.model;

import api.domains.entity.Consultation;
import api.enums.Specialty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    private Long id;
    private String name;
    private String crm;
    private Specialty specialty;
    private ClinicDto clinic;
    private List<ConsultationDto> consultations;
}
