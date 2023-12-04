package api.domains.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

        private Long id;
        private String name;
        private String cpf;
        private Integer age;
        private Integer rg;
        private LocalDate dateOfBirth;
        private String phone;
        private AddressDto address;
}
