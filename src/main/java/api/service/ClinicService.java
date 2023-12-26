package api.service;

import api.domains.entity.Address;
import api.domains.entity.Clinic;
import api.domains.model.AddressDto;
import api.domains.model.ClinicDto;
import api.domains.repository.AddressRepository;
import api.domains.repository.ClinicRepository;
import api.exception.handlers.HandlerEntityNotFoundException;
import api.exception.handlers.HandlerError;
import api.rest.request.ClinicRequest;
import api.rest.response.ClinicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClinicService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClinicRepository clinicRepository;

    public ClinicResponse createClinic(ClinicRequest clinicRequest){
        var addressRequest = clinicRequest.getAddress();
        try {
            Address address = new Address();
            address.setAddress(addressRequest.getAddress());
            address.setCep(addressRequest.getCep());
            address.setComplement(addressRequest.getComplement());
            address.setNumber(addressRequest.getNumber());
            address.setDistrict(addressRequest.getDistrict());
            address.setState(addressRequest.getState());
            addressRepository.save(address);

            Clinic clinic = new Clinic();
            clinic.setName(clinicRequest.getName());
            clinic.setCnpj(clinicRequest.getCnpj());
            clinic.setAddress(address);
            clinicRepository.save(clinic);

            return new ClinicResponse("Create clinic successfully");
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public List<ClinicResponse> findAllClinic(){
        List<Clinic> clinics = clinicRepository.findAll();
        List<ClinicResponse> responses = new ArrayList<>();
        try {
            clinics.forEach(clinic -> {
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
                ClinicResponse clinicResponse =
                        new ClinicResponse(ClinicDto.builder()
                                .id(clinic.getId())
                                .name(clinic.getName())
                                .cnpj(clinic.getCnpj())
                                .address(addressDto)
                                .build());
                responses.add(clinicResponse);
            });
            return responses;
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public ClinicResponse findByClinic(Long idClinic){
        Clinic clinic = clinicRepository.findById(idClinic)
                .orElseThrow(()->new HandlerEntityNotFoundException("User not found with id"+idClinic));
        try {
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

            return new ClinicResponse(ClinicDto.builder()
                    .id(clinic.getId())
                    .name(clinic.getName())
                    .cnpj(clinic.getCnpj())
                    .address(addressDto)
                    .build());
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
    public ClinicResponse updateClinic(ClinicRequest clinicRequest,Long idClinic){
        Clinic clinic = clinicRepository.findById(idClinic)
                .orElseThrow(()->new HandlerEntityNotFoundException("User not found with id"+idClinic));
        Address address = addressRepository.findById(clinicRequest.getAddress().getId())
                .orElseThrow(()->new HandlerEntityNotFoundException("User not found with id"+clinicRequest.getAddress().getId()));
        var addressRequest = clinicRequest.getAddress();
        try {
            address.setAddress(addressRequest.getAddress());
            address.setCep(addressRequest.getCep());
            address.setComplement(addressRequest.getComplement());
            address.setNumber(addressRequest.getNumber());
            address.setDistrict(addressRequest.getDistrict());
            address.setState(addressRequest.getState());
            addressRepository.save(address);

            clinic.setName(clinicRequest.getName());
            clinic.setCnpj(clinicRequest.getCnpj());
            clinic.setAddress(address);
            clinicRepository.save(clinic);

            return new ClinicResponse("Update clinic successfully");
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }

    public ClinicResponse deleteClinic(Long idClinic){
        Clinic clinic = clinicRepository.findById(idClinic)
                .orElseThrow(()->new HandlerEntityNotFoundException("User not found with id"+idClinic));
        try{
            clinicRepository.delete(clinic);
            return new ClinicResponse("Delete clinic successufully");
        }catch (Exception ex){
            throw new HandlerError(ex.getMessage());
        }
    }
}
