package api.rest.response;

import api.domains.model.ClinicDto;
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
public class ClinicResponse {

    private ClinicDto clinic;

    private String msg;

    public ClinicResponse(ClinicDto clinic) {
        this.clinic = clinic;
    }

    public ClinicResponse(String msg) {
        this.msg = msg;
    }
}
