package com.dxc.ppm.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "MedicalTreatmentProfile")
public class MedicalTreatmentProfile {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PATIENT_ID", nullable = false)
    private String patientId;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "DOCTOR")
    private String doctor;

    @Column(name = "DISEASES_HISTORY")
    private List<String> diseasesHistory;

    @Column(name = "PRESCRIPTIONS")
    private String prescriptions;

    @Column(name = "MEDICAL_TEST_RESULT")
    private String medicalTestResult;
}