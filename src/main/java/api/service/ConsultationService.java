package api.service;

import api.domains.entity.Consultation;
import api.domains.entity.Doctor;
import api.domains.entity.Patient;
import api.domains.entity.User;
import api.domains.model.AddressDto;
import api.domains.model.ConsultationDto;
import api.domains.model.DoctorDto;
import api.domains.model.PatientDto;
import api.domains.repository.AddressRepository;
import api.domains.repository.ConsultationRepository;
import api.domains.repository.DoctorRepository;
import api.domains.repository.PatientRepository;
import api.exception.handlers.HandlerEntityNotFoundException;
import api.exception.handlers.HandlerError;
import api.rest.request.ConsultationRequest;
import api.rest.response.ClinicResponse;
import api.rest.response.ConsultationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultationService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ConsultationRepository consultationRepository;

    public ConsultationResponse createConsultation(ConsultationRequest consultationRequest,Long idDoctor,Long idPatient){
        Doctor doctor = doctorRepository.findById(idDoctor)
                .orElseThrow(()->new HandlerEntityNotFoundException("Doctor not found whith id"+idDoctor));
        Patient patient = patientRepository.findById(idPatient)
                .orElseThrow(()->new HandlerEntityNotFoundException("Patient not found whith id"+idPatient));
        try {
            Consultation consultation = new Consultation();
            consultation.setConsultationDate(consultationRequest.getConsultationDate());
            consultation.setDescription(consultationRequest.getDescription());
            consultation.setStatusConsltation(consultationRequest.getStatusConsltation());
            consultation.setDoctor(doctor);
            consultation.setPatient(patient);
            consultationRepository.save(consultation);

            return new ConsultationResponse("Create consultation successfully");
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public List<ConsultationResponse> findAllConsultation(){
        List<Consultation> consultations = consultationRepository.findAll();
        List<ConsultationResponse> responses = new ArrayList<>();
        try {
            consultations.forEach(consultation -> {
                var patient = consultation.getPatient();
                var address = patient.getAddress();
                var doctor = consultation.getDoctor();
                AddressDto addressDto = AddressDto.builder()
                        .id(address.getId())
                        .address(address.getAddress())
                        .cep(address.getCep())
                        .state(address.getState())
                        .complement(address.getComplement())
                        .district(address.getDistrict())
                        .number(address.getNumber())
                        .build();
                PatientDto patientDto = PatientDto.builder()
                        .id(patient.getId())
                        .cpf(patient.getCpf())
                        .rg(patient.getRg())
                        .age(patient.getAge())
                        .name(patient.getName())
                        .address(addressDto)
                        .phone(patient.getPhone())
                        .dateOfBirth(patient.getDateOfBirth())
                        .build();
                DoctorDto doctorDto = DoctorDto.builder()
                        .id(doctor.getId())
                        .crm(doctor.getCrm())
                        .name(doctor.getName())
                        .specialty(doctor.getSpecialty())
                        .build();
                ConsultationResponse consultationResponse =
                        new ConsultationResponse(ConsultationDto.builder()
                                .id(consultation.getId())
                                .consultationDate(consultation.getConsultationDate())
                                .description(consultation.getDescription())
                                .doctor(doctorDto)
                                .patient(patientDto)
                                .build());
                responses.add(consultationResponse);
            });
            return responses;
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public ConsultationResponse findByConsultation(Long idConsultation){
        Consultation consultation = consultationRepository.findById(idConsultation)
                .orElseThrow(()->new HandlerEntityNotFoundException("Consultation not found whith id"+idConsultation));
        try {
            var patient = consultation.getPatient();
            var address = patient.getAddress();
            var doctor = consultation.getDoctor();
            AddressDto addressDto = AddressDto.builder()
                    .id(address.getId())
                    .address(address.getAddress())
                    .cep(address.getCep())
                    .state(address.getState())
                    .complement(address.getComplement())
                    .district(address.getDistrict())
                    .number(address.getNumber())
                    .build();
            PatientDto patientDto = PatientDto.builder()
                    .id(patient.getId())
                    .cpf(patient.getCpf())
                    .rg(patient.getRg())
                    .age(patient.getAge())
                    .name(patient.getName())
                    .address(addressDto)
                    .phone(patient.getPhone())
                    .dateOfBirth(patient.getDateOfBirth())
                    .build();
            DoctorDto doctorDto = DoctorDto.builder()
                    .id(doctor.getId())
                    .crm(doctor.getCrm())
                    .name(doctor.getName())
                    .specialty(doctor.getSpecialty())
                    .build();
            return new ConsultationResponse(ConsultationDto.builder()
                            .id(consultation.getId())
                            .consultationDate(consultation.getConsultationDate())
                            .description(consultation.getDescription())
                            .doctor(doctorDto)
                            .patient(patientDto)
                            .build());
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public ConsultationResponse updateConsultation(ConsultationRequest consultationRequest,Long idConsultation){
        Doctor doctor = doctorRepository.findById(consultationRequest.getDoctor().getId())
                .orElseThrow(()->new HandlerEntityNotFoundException("Doctor not found whith id"+consultationRequest.getDoctor().getId()));
        Patient patient = patientRepository.findById(consultationRequest.getPatient().getId())
                .orElseThrow(()->new HandlerEntityNotFoundException("Patient not found whith id"+consultationRequest.getPatient().getId()));
        Consultation consultation = consultationRepository.findById(idConsultation)
                .orElseThrow(()->new HandlerEntityNotFoundException("Consultation not found whith id"+idConsultation));
        try {
            consultation.setConsultationDate(consultationRequest.getConsultationDate());
            consultation.setDescription(consultationRequest.getDescription());
            consultation.setStatusConsltation(consultationRequest.getStatusConsltation());
            consultation.setDoctor(doctor);
            consultation.setPatient(patient);
            consultationRepository.save(consultation);

            return new ConsultationResponse("Update consultation successfully");
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public ConsultationResponse deleteConsultation(Long idConsultation){
        Consultation consultation = consultationRepository.findById(idConsultation)
                .orElseThrow(()->new HandlerEntityNotFoundException("Consultation not found whith id"+idConsultation));
        try {
            consultationRepository.delete(consultation);
            return new ConsultationResponse("Delete consultation successfully");
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
}
