package api.domains.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {

    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;


}
