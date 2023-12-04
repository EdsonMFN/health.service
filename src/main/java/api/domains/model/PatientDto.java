package api.domains.model;

import api.domains.entity.Consultation;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto {

        private Long id;
        private String name;
        private String cpf;
        private Integer age;
        private Integer rg;
        private LocalDate dateOfBirth;
        private String phone;
        private AddressDto address;
        private List<ConsultationDto> consultations;
}
