package api.service;

import api.domains.entity.Clinic;
import api.domains.entity.Doctor;
import api.domains.entity.User;
import api.domains.model.*;
import api.domains.repository.ClinicRepository;
import api.domains.repository.DoctorRepository;
import api.domains.repository.UserRepository;
import api.exception.handlers.HandlerEntityNotFoundException;
import api.exception.handlers.HandlerError;
import api.rest.request.DoctorRequest;
import api.rest.response.ClinicResponse;
import api.rest.response.DoctorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ClinicRepository clinicRepository;

    public DoctorResponse createDoctor(DoctorRequest doctorRequest, Long idClinic){
        Clinic clinic = clinicRepository.findById(idClinic)
                .orElseThrow(()->new HandlerEntityNotFoundException("Clinic not found whith id"+idClinic));
        try {
            Doctor doctor = new Doctor();
            doctor.setName(doctorRequest.getName());
            doctor.setCrm(doctorRequest.getCrm());
            doctor.setSpecialty(doctorRequest.getSpecialty());
            doctor.setClinic(clinic);
            doctorRepository.save(doctor);

            return new DoctorResponse("Create doctor seccessflly");
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public List<DoctorResponse> findAllDoctor(){
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorResponse> responses = new ArrayList<>();
        List<ConsultationDto> consultations = new ArrayList<>();
        try {
            doctors.forEach(doctor -> {
                var clinic = doctor.getClinic();
                var address = clinic.getAddress();
                AddressDto addressDto = AddressDto.builder()
                        .id(address.getId())
                        .address(address.getAddress())
                        .cep(address.getCep())
                        .state(address.getState())
                        .complement(address.getComplement())
                        .district(address.getDistrict())
                        .number(address.getNumber())
                        .build();
                ClinicDto clinicDto = ClinicDto.builder()
                        .id(clinic.getId())
                        .name(clinic.getName())
                        .address(addressDto)
                        .cnpj(clinic.getCnpj())
                        .build();
                doctor.getConsultations()
                        .forEach(consultation -> {
                            var patient = consultation.getPatient();

                            PatientDto patientDto = PatientDto.builder()
                                    .id(patient.getId())
                                    .cpf(patient.getCpf())
                                    .rg(patient.getRg())
                                    .age(patient.getAge())
                                    .name(patient.getName())
                                    .phone(patient.getPhone())
                                    .dateOfBirth(patient.getDateOfBirth())
                                    .build();

                            ConsultationDto consultationDto =
                                    ConsultationDto.builder()
                                    .id(consultation.getId())
                                    .consultationDate(consultation.getConsultationDate())
                                    .description(consultation.getDescription())
                                    .patient(patientDto)
                                    .build();
                            consultations.add(consultationDto);
                });
                DoctorResponse doctorResponse =
                        new DoctorResponse(DoctorDto.builder()
                                .id(doctor.getId())
                                .clinic(clinicDto)
                                .crm(doctor.getCrm())
                                .name(doctor.getName())
                                .specialty(doctor.getSpecialty())
                                .consultations(consultations)
                                .build());
                responses.add(doctorResponse);
            });
            return responses;
        }catch  (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public DoctorResponse findByDoctor(Long idDoctor){
        Doctor doctor = doctorRepository.findById(idDoctor)
                .orElseThrow(()->new HandlerEntityNotFoundException("Doctor not found whith id"+idDoctor));
        var clinic = doctor.getClinic();
        var address = clinic.getAddress();
        try {
            AddressDto addressDto = AddressDto.builder()
                    .id(address.getId())
                    .address(address.getAddress())
                    .cep(address.getCep())
                    .state(address.getState())
                    .complement(address.getComplement())
                    .district(address.getDistrict())
                    .number(address.getNumber())
                    .build();
            ClinicDto clinicDto = ClinicDto.builder()
                    .id(clinic.getId())
                    .name(clinic.getName())
                    .address(addressDto)
                    .cnpj(clinic.getCnpj())
                    .build();

            return new DoctorResponse(DoctorDto.builder()
                    .id(doctor.getId())
                    .clinic(clinicDto)
                    .crm(doctor.getCrm())
                    .name(doctor.getName())
                    .specialty(doctor.getSpecialty())
                    .build());
        }catch  (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public DoctorResponse updateDoctor(DoctorRequest doctorRequest, Long idDoctor){
        Clinic clinic = clinicRepository.findById(doctorRequest.getClinic().getId())
                .orElseThrow(()->new HandlerEntityNotFoundException("Clinic not found whith id"+ doctorRequest.getClinic().getId()));
        Doctor doctor = doctorRepository.findById(idDoctor)
                .orElseThrow(()->new HandlerEntityNotFoundException("Doctor not found whith id"+idDoctor));
        try {
            doctor.setName(doctorRequest.getName());
            doctor.setCrm(doctorRequest.getCrm());
            doctor.setSpecialty(doctorRequest.getSpecialty());
            doctor.setClinic(clinic);
            doctorRepository.save(doctor);

            return new DoctorResponse("Create doctor seccessflly");
        }catch  (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public DoctorResponse deleteDoctor(Long idDoctor){
        Doctor doctor = doctorRepository.findById(idDoctor)
                .orElseThrow(()->new HandlerEntityNotFoundException("Doctor not found whith id"+idDoctor));
        try {
            doctorRepository.delete(doctor);
            return new DoctorResponse("Doctor delete sucessully");
        }catch  (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
}
