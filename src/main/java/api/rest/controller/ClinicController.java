package api.rest.controller;

import api.rest.request.ClinicRequest;
import api.rest.request.UserRequest;
import api.rest.response.ClinicResponse;
import api.rest.response.UserResponse;
import api.service.ClinicService;
import api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clinic")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @PostMapping
    public ResponseEntity<ClinicResponse> createClinic(@RequestBody ClinicRequest clinicRequest){
        ClinicResponse response = clinicService.createClinic(clinicRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping(value = "/allClinic")
    public ResponseEntity<List<ClinicResponse>>findAllClinic(){
        List<ClinicResponse> responses = clinicService.findAllClinic();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
    @GetMapping(value = "/{idClinic}")
    public ResponseEntity<ClinicResponse>findByClinic(@PathVariable Long idClinic){
        ClinicResponse response = clinicService.findByClinic(idClinic);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping(value = "/{idClinic}")
    public ResponseEntity<ClinicResponse>updateClinic(@RequestBody ClinicRequest clinicRequest,@PathVariable Long idClinic){
        ClinicResponse response = clinicService.updateClinic(clinicRequest,idClinic);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping(value = "/{idClinic}")
    public ResponseEntity<ClinicResponse>deleteClinic(@PathVariable Long idClinic){
        ClinicResponse response = clinicService.deleteClinic(idClinic);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
