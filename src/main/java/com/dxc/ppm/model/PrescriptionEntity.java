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

    public MedicalTreatmentProfileEntity getMedicalTreatmentProfileEntity() {
        return medicalTreatmentProfileEntity;
    }

    public void setMedicalTreatmentProfileEntity(MedicalTreatmentProfileEntity medicalTreatmentProfileEntity) {
        this.medicalTreatmentProfileEntity = medicalTreatmentProfileEntity;
    }

    public List<GivenMedicine> getGivenMedicines() {
        return givenMedicines;
    }

    public void setGivenMedicines(List<GivenMedicine> givenMedicines) {
        this.givenMedicines = givenMedicines;
    }
}