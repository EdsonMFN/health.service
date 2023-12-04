package api.rest.controller;

import api.rest.request.DoctorRequest;
import api.rest.request.UserRequest;
import api.rest.response.DoctorResponse;
import api.rest.response.UserResponse;
import api.service.DoctorService;
import api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping(value = "/clinic/{idClinic}")
    public ResponseEntity<DoctorResponse> createDoctor(@RequestBody DoctorRequest doctorRequest,@PathVariable Long idClinic){
        DoctorResponse response = doctorService.createDoctor(doctorRequest,idClinic);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping(value = "/allDoctor")
    public ResponseEntity<List<DoctorResponse>>findAllDoctor(){
        List<DoctorResponse> responses = doctorService.findAllDoctor();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
    @GetMapping(value = "/{idDoctor}")
    public ResponseEntity<DoctorResponse>findByDoctor(@PathVariable Long idDoctor){
        DoctorResponse response = doctorService.findByDoctor(idDoctor);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping(value = "/{idDoctor}")
    public ResponseEntity<DoctorResponse>updateDoctor(@RequestBody DoctorRequest doctorRequest,@PathVariable Long idDoctor){
        DoctorResponse response = doctorService.updateDoctor(doctorRequest,idDoctor);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping(value = "/{idDoctor}")
    public ResponseEntity<DoctorResponse>deleteDoctor(@PathVariable Long idDoctor){
        DoctorResponse response = doctorService.deleteDoctor(idDoctor);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
