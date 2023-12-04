package api.domains.entity;

import api.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "consultation")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consultation")
    @PrimaryKeyJoinColumns(value = @PrimaryKeyJoinColumn)
    private Long id;

    @Column(name = "consultation_date")
    private LocalDate consultationDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status_consltation")
    @Enumerated(EnumType.STRING)
    private Status statusConsltation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctor")
    private Doctor doctors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient")
    private Patient patients;
}
