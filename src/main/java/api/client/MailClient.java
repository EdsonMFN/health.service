package api.client;

import api.config.ConfigFeingClient;
import api.rest.request.MailRequest;
import api.rest.response.MailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${mail-health-name}",url = "${mail-health-url-base}",configuration = ConfigFeingClient.class)
public interface MailClient {

    @PostMapping
    MailResponse sendMail(@RequestBody MailRequest request);
}
