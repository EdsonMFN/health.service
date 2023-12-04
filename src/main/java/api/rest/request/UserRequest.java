package api.rest.request;

import api.domains.model.PatientDto;
import api.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;
    private String username;
    private String password;
    private UserRole role;
    private String email;
    private PatientDto patient;
}
