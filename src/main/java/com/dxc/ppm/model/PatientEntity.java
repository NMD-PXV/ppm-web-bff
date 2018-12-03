package com.dxc.ppm.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patient")
public class PatientEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PATIENT_ID")
    @PrimaryKeyJoinColumn
    private String patient_id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENT_ID", insertable = false, updatable = false)
    private PersonalInfoEntity personalInfoEntity;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENT_ID", insertable = false, updatable = false)
    private List<MedicalTreatmentProfileEntity> medicalTreatmentProfileEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public PersonalInfoEntity getPersonalInfoEntity() {
        return personalInfoEntity;
    }

    public void setPersonalInfoEntity(PersonalInfoEntity personalInfoEntity) {
        this.personalInfoEntity = personalInfoEntity;
    }

    public List<MedicalTreatmentProfileEntity> getMedicalTreatmentProfileEntities() {
        return medicalTreatmentProfileEntities;
    }

    public void setMedicalTreatmentProfileEntities(List<MedicalTreatmentProfileEntity> medicalTreatmentProfileEntities) {
        this.medicalTreatmentProfileEntities = medicalTreatmentProfileEntities;
    }
}
