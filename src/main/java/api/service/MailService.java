package api.service;


import api.client.MailClient;
import api.domains.entity.Patient;
import api.domains.repository.PatientRepository;
import api.exception.handlers.HandlerEntityNotFoundException;
import api.rest.request.MailRequest;
import api.rest.response.MailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private MailClient sender;
    @Autowired
    private PatientRepository patientRepository;

    public MailResponse sendEmail(MailRequest request){
        Patient patient = patientRepository.findByName(request.getOwnerRef())
                .orElseThrow(()->new HandlerEntityNotFoundException("User not found with id"+ request.getOwnerRef()));
        try {
            request.setOwnerRef(patient.getName());
            sender.sendMail(request);
            return new MailResponse("successs");
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
