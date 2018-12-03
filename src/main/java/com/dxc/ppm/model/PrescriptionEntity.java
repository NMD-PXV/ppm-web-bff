package com.dxc.ppm.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PrescriptionEntity")
public class PrescriptionEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "MEDICAL_TREATMENT_PROFILE_ENTITY_ID")
    private MedicalTreatmentProfileEntity medicalTreatmentProfileEntity;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRESCRIPTION_ID")
    private List<GivenMedicine> givenMedicines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}