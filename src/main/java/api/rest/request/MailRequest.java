package api.rest.request;


import api.enums.StatusEmail;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MailRequest {

    private Long emailId;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;
}
