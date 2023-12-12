package api.client;

import api.config.ConfigFeingClient;
import api.rest.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "${auth-health-name}",url = "${auth-health-url-base}",configuration = ConfigFeingClient.class)
public interface AuthClient {

    @GetMapping("/auth")
    ResponseEntity<UserResponse> authenticateUser(@RequestHeader("Authorization") String authorization);
}
