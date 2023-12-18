package api.rest.controller;


import api.rest.request.MailRequest;
import api.rest.response.MailResponse;
import api.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mail")
public class MailController {

    @Autowired
    private MailService service;

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<MailResponse> sendEmail(@RequestBody MailRequest request){
        MailResponse response = service.sendEmail(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
