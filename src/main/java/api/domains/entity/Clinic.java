package api.domains.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

@Table(name = "clinic")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clinic")
    @PrimaryKeyJoinColumns(value = @PrimaryKeyJoinColumn)
    private Long id;

    @Column(name = "cnpj")
    @CNPJ
    private String cnpj;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;
}
