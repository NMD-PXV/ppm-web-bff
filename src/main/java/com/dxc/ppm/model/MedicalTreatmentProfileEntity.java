package com.dxc.ppm.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MedicalTreatmentProfileEntity")
public class MedicalTreatmentProfileEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PATIENT_ID")
    @PrimaryKeyJoinColumn
    private String patient_id;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "DOCTOR")
    private String doctor;

    @Column(name = "DISEASES_HISTORY")
    private String diseasesHistory;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "PRESCRIPTION_ID")
    private PrescriptionEntity prescription;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "MEDICAL_TEST_RESULT_ID")
    private MedicalTestResultEntity medicalTestResultEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENT_ID", insertable = false, updatable = false)
    private PatientEntity patientEntity;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDiseasesHistory() {
        return diseasesHistory;
    }

    public void setDiseasesHistory(String diseasesHistory) {
        this.diseasesHistory = diseasesHistory;
    }

    public PrescriptionEntity getPrescription() {
        return prescription;
    }

    public void setPrescription(PrescriptionEntity prescription) {
        this.prescription = prescription;
    }

    public MedicalTestResultEntity getMedicalTestResultEntity() {
        return medicalTestResultEntity;
    }

    public void setMedicalTestResultEntity(MedicalTestResultEntity medicalTestResultEntity) {
        this.medicalTestResultEntity = medicalTestResultEntity;
    }

    public PatientEntity getPatientEntity() {
        return patientEntity;
    }

    public void setPatientEntity(PatientEntity patientEntity) {
        this.patientEntity = patientEntity;
    }
}