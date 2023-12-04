package api.rest.request;

import api.domains.model.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRequest {

    private Long id;
    private String cnpj;
    private String name;
    private AddressDto address;
}
