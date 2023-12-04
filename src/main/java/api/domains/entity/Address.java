package api.domains.entity;

import api.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "address")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    @PrimaryKeyJoinColumns(value = @PrimaryKeyJoinColumn)
    private Long id;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "number",nullable = false)
    private int number;

    @Column(name = "state",nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "district",nullable = false)
    private String district;

    @Column(name = "complement")
    private String complement;

    @Column(name = "cep")
    private String cep;
}
