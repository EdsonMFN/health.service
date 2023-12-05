package api.domains.model;

import api.enums.State;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long id;
    private String address;
    private int number;
    private State state;
    private String district;
    private String complement;
    private String cep;
}
