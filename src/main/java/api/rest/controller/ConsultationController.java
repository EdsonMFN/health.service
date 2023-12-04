package api.rest.controller;

import api.rest.request.ConsultationRequest;
import api.rest.request.DoctorRequest;
import api.rest.response.ConsultationResponse;
import api.rest.response.DoctorResponse;
import api.service.ConsultationService;
import api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consultation")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping(value = "/doctor/{idDoctor}/patient/{idPatient}")
    public ResponseEntity<ConsultationResponse> createConsultation(@RequestBody ConsultationRequest consultationRequest,
                                                                   @PathVariable Long idDoctor,
                                                                   @PathVariable Long idPatient){
        ConsultationResponse response = consultationService.createConsultation(consultationRequest,idDoctor,idPatient);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping(value = "/allConsultation")
    public ResponseEntity<List<ConsultationResponse>>findAllConsultation(){
        List<ConsultationResponse> responses = consultationService.findAllConsultation();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
    @GetMapping(value = "/{idConsultation}")
    public ResponseEntity<ConsultationResponse>findByConsultation(@PathVariable Long idConsultation){
        ConsultationResponse response = consultationService.findByConsultation(idConsultation);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping(value = "/{idConsultation}")
    public ResponseEntity<ConsultationResponse>updateConsultation(@RequestBody ConsultationRequest consultationRequest,
                                                                  @PathVariable Long idConsultation){
        ConsultationResponse response = consultationService.updateConsultation(consultationRequest,idConsultation);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping(value = "/{idConsultation}")
    public ResponseEntity<ConsultationResponse>deleteConsultation(@PathVariable Long idConsultation){
        ConsultationResponse response = consultationService.deleteConsultation(idConsultation);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
