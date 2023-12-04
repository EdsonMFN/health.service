package api.service;

import api.domains.entity.Address;
import api.domains.entity.Patient;
import api.domains.entity.User;
import api.domains.model.AddressDto;
import api.domains.model.ConsultationDto;
import api.domains.model.PatientDto;
import api.domains.model.UserDto;
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
            patient.setRg(patientRequest.getRg());
            patient.setAge(patientRequest.getAge());
            patient.setDateOfBirth(patientRequest.getDateOfBirth());
            patient.setPhone(patientRequest.getPhone());
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
            patient.getConsultations().forEach
                    (consultation -> {
                        ConsultationDto consultationDto =
                                ConsultationDto.builder()
                                        .id(consultation.getId())
                                        .consultationDate(consultation.getConsultationDate())
                                        .description(consultation.getDescription())
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
            patient.setRg(patientRequest.getRg());
            patient.setAge(patientRequest.getAge());
            patient.setDateOfBirth(patientRequest.getDateOfBirth());
            patient.setPhone(patientRequest.getPhone());
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
}
