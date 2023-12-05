package api.service;

import api.domains.entity.Address;
import api.domains.entity.Patient;
import api.domains.entity.User;
import api.domains.model.*;
import api.domains.repository.AddressRepository;
import api.domains.repository.PatientRepository;
import api.domains.repository.UserRepository;
import api.exception.handlers.HandlerEntityNotFoundException;
import api.exception.handlers.HandlerError;
import api.rest.request.UserRequest;
import api.rest.response.ClinicResponse;
import api.rest.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    public UserResponse createUser(UserRequest userRequest){

        var patientRequest = userRequest.getPatient();
        var addressRequest = patientRequest.getAddress();
        try {
            Address address = new Address();
            address.setAddress(addressRequest.getAddress());
            address.setCep(addressRequest.getCep());
            address.setComplement(addressRequest.getComplement());
            address.setNumber(addressRequest.getNumber());
            address.setDistrict(addressRequest.getDistrict());
            address.setState(addressRequest.getState());
            addressRepository.save(address);

            Patient patient = new Patient();
            patient.setName(patientRequest.getName());
            patient.setCpf(patientRequest.getCpf());
            patient.setRg(rgFormat(patientRequest.getRg()));
            patient.setAge(patientRequest.getAge());
            patient.setDateOfBirth(patientRequest.getDateOfBirth());
            patient.setPhone(phoneFormat(patientRequest.getPhone()));
            patient.setAddress(address);
            patientRepository.save(patient);

            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());
            user.setEmail(userRequest.getEmail());
            user.setPatient(patient);
            userRepository.save(user);

            return new UserResponse("Create user successfully");
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }

    }
    public List<UserResponse> findAllUser(){
        List<User> users = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();
        List<ConsultationDto> consultations = new ArrayList<>();
        try {
            users.forEach(user -> {
                var patient = user.getPatient();
                var address = patient.getAddress();
                AddressDto addressDto = AddressDto.builder()
                        .id(address.getId())
                        .address(address.getAddress())
                        .cep(address.getCep())
                        .state(address.getState())
                        .complement(address.getComplement())
                        .district(address.getDistrict())
                        .number(address.getNumber())
                        .build();

                patient.getConsultations().forEach(consultation -> {
                    var doctor = consultation.getDoctor();
                    DoctorDto doctorDto = DoctorDto.builder()
                            .id(doctor.getId())
                            .crm(doctor.getCrm())
                            .name(doctor.getName())
                            .specialty(doctor.getSpecialty())
                            .build();
                    ConsultationDto consultationDto =ConsultationDto.builder()
                            .id(consultation.getId())
                            .consultationDate(consultation.getConsultationDate())
                            .description(consultation.getDescription())
                            .doctor(doctorDto)
                            .build();
                    consultations.add(consultationDto);
                });
                PatientDto patientDto = PatientDto.builder()
                        .id(patient.getId())
                        .cpf(patient.getCpf())
                        .rg(patient.getRg())
                        .age(patient.getAge())
                        .name(patient.getName())
                        .address(addressDto)
                        .phone(patient.getPhone())
                        .dateOfBirth(patient.getDateOfBirth())
                        .consultations(consultations)
                        .build();
                UserResponse userResponse = new UserResponse(UserDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .patient(patientDto)
                        .password(user.getPassword())
                        .username(user.getUsername())
                        .build());
                responses.add(userResponse);
            });
            return responses;

        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public UserResponse findByUser(Long idUser){
        User user = userRepository.findById(idUser)
                .orElseThrow(()->new HandlerEntityNotFoundException("User not found whith id"+idUser));
        List<ConsultationDto> consultations = new ArrayList<>();
        var patient = user.getPatient();
        var address = patient.getAddress();
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
            patient.getConsultations().forEach(consultation -> {
                var doctor = consultation.getDoctor();
                DoctorDto doctorDto = DoctorDto.builder()
                        .id(doctor.getId())
                        .crm(doctor.getCrm())
                        .name(doctor.getName())
                        .specialty(doctor.getSpecialty())
                        .build();
                ConsultationDto consultationDto =ConsultationDto.builder()
                        .id(consultation.getId())
                        .consultationDate(consultation.getConsultationDate())
                        .description(consultation.getDescription())
                        .doctor(doctorDto)
                        .build();
                consultations.add(consultationDto);
            });
            PatientDto patientDto = PatientDto.builder()
                    .id(patient.getId())
                    .cpf(patient.getCpf())
                    .rg(rgFormat(patient.getRg()))
                    .age(patient.getAge())
                    .name(patient.getName())
                    .address(addressDto)
                    .phone(phoneFormat(patient.getPhone()))
                    .dateOfBirth(patient.getDateOfBirth())
                    .consultations(consultations)
                    .build();
            return new UserResponse(UserDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .patient(patientDto)
                    .password(user.getPassword())
                    .username(user.getUsername())
                    .build());
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public UserResponse updateUser(UserRequest userRequest,Long idUser){
        User user = userRepository.findById(idUser)
                .orElseThrow(()->new HandlerEntityNotFoundException("User not found whith id"+idUser));
        var patientRequest = userRequest.getPatient();
        var addressRequest = patientRequest.getAddress();
        try {
            Address address = new Address();
            address.setAddress(addressRequest.getAddress());
            address.setCep(addressRequest.getCep());
            address.setComplement(addressRequest.getComplement());
            address.setNumber(addressRequest.getNumber());
            address.setDistrict(addressRequest.getDistrict());
            address.setState(addressRequest.getState());
            addressRepository.save(address);

            Patient patient = new Patient();
            patient.setName(patientRequest.getName());
            patient.setCpf(patientRequest.getCpf());
            patient.setRg(rgFormat(patientRequest.getRg()));
            patient.setAge(patientRequest.getAge());
            patient.setDateOfBirth(patientRequest.getDateOfBirth());
            patient.setPhone(phoneFormat(patientRequest.getPhone()));
            patient.setAddress(address);
            patientRepository.save(patient);

            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());
            user.setEmail(userRequest.getEmail());
            user.setPatient(patient);
            userRepository.save(user);

            return new UserResponse("Update user successfully");
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public UserResponse deleteUser(Long idUser){
        User user = userRepository.findById(idUser)
                .orElseThrow(()->new HandlerEntityNotFoundException("User not found whith id"+idUser));
        try {
            userRepository.delete(user);
            return new UserResponse("User delete successfully");
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    private String phoneFormat(String phone){
        if (phone.length() >= 11 && phone.matches("\\d+")){
            return String.format("(%s)%s",
                    phone.substring(0,2),
                    phone.substring(2,11));
        }else {
            throw new HandlerError("Invalid phone number, enter numbers only!");
        }
    }
    private String rgFormat (String rg){
        if (rg.length() >= 7 && rg.matches("\\d+")){
            return String.format("%s.%s.%s",
                    rg.charAt(0),
                    rg.substring(1,4),
                    rg.substring(4,7));
        }else {
            throw new HandlerError("Invalid rg number, enter numbers only!");
        }
    }
}
