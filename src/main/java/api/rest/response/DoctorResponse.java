package api.rest.response;

import api.domains.model.ConsultationDto;
import api.domains.model.DoctorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponse {
    private DoctorDto doctor;

    private String msg;

    public DoctorResponse(DoctorDto doctor) {
        this.doctor = doctor;
    }

    public DoctorResponse(String msg) {
        this.msg = msg;
    }
}
