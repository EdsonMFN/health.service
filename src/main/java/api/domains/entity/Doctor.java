package api.domains.entity;

import api.enums.Specialty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "doctor")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "crm")
    private String crm;

    @Column(name = "specialty")
    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clinic")
    private Clinic clinic;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<Consultation> consultations;
}
