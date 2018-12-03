package com.dxc.ppm.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PersonalInfoEntity")
public class PersonalInfoEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PATIENT_ID")
    @PrimaryKeyJoinColumn
    private String patient_id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "POD")
    private String pob;

    @Column(name = "SEX", length = 1)
    private String sex;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public PatientEntity getPatientEntity() {
        return patientEntity;
    }

    public void setPatientEntity(PatientEntity patientEntity) {
        this.patientEntity = patientEntity;
    }
}
