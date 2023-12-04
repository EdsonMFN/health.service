package api.rest.response;

import api.domains.model.ClinicDto;
import api.domains.model.ConsultationDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultationResponse {
    private ConsultationDto consultation;

    private String msg;

    public ConsultationResponse(ConsultationDto consultation) {
        this.consultation = consultation;
    }

    public ConsultationResponse(String msg) {
        this.msg = msg;
    }
}
