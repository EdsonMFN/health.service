package api.domains.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @PrimaryKeyJoinColumns(value = @PrimaryKeyJoinColumn)
        @Column(name = "id_patient")
        private Long id;

        @Column(name = "name",nullable = false)
        private String name;

        @Column(name = "cpf",nullable = false,unique = true)
        @CPF
        private String cpf;

        @Column(name = "age",nullable = false)
        private Integer age;

        @Column(name = "rg",nullable = false)
        private String rg;

        @Column(name = "date_of_birth",nullable = false)
        private LocalDate dateOfBirth;

        @Column(name = "phone",nullable = false,unique = true)
        private String phone;

        @OneToOne
        @JoinColumn(name = "id_address")
        private Address address;

        @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
        private List<Consultation> consultations;
}
