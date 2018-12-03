package com.dxc.ppm.model;

import javax.persistence.*;

@Entity
@Table(name = "MedicalTestResult")
public class MedicalTestResultEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "BLOODTYPE")
    private String bloodType;

    @Column(name = "XRAY")
    private String xray;

    @Column(name = "ULTRASOUND")
    private String ultraSound;

    @Column(name = "ALLERGIC_MEDICINES", columnDefinition = "LONGTEXT")
    private String allergicMedicines;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "MEDICAL_TREATMENT_PROFILE_ID")
    private MedicalTreatmentProfileEntity medicalTreatmentProfileEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getXray() {
        return xray;
    }

    public void setXray(String xray) {
        this.xray = xray;
    }

    public String getUltraSound() {
        return ultraSound;
    }

    public void setUltraSound(String ultraSound) {
        this.ultraSound = ultraSound;
    }

    public String getAllergicMedicines() {
        return allergicMedicines;
    }

    public void setAllergicMedicines(String allergicMedicines) {
        this.allergicMedicines = allergicMedicines;
    }

    public MedicalTreatmentProfileEntity getMedicalTreatmentProfileEntity() {
        return medicalTreatmentProfileEntity;
    }

    public void setMedicalTreatmentProfileEntity(MedicalTreatmentProfileEntity medicalTreatmentProfileEntity) {
        this.medicalTreatmentProfileEntity = medicalTreatmentProfileEntity;
    }
}
