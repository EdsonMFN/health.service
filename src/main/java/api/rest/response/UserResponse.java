package api.rest.response;

import api.domains.model.ConsultationDto;
import api.domains.model.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private UserDto user;

    private String msg;

    public UserResponse(UserDto user) {
        this.user = user;
    }

    public UserResponse(String msg) {
        this.msg = msg;
    }
}
