package api.rest.request;

import api.domains.model.ClinicDto;
import api.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {

    private Long id;
    private String name;
    private String crm;
    private Specialty specialty;
    private ClinicDto clinic;
}
