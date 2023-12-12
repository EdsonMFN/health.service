package api.exception.handlers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAuthenticationError {
    private String error;
    private String errorDescription;
    private String errorUri;
}
