package com.dxc.ppm.model;

import javax.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PATIENT_ID", length = 36)
    private String patientId;

    @Column(name = "JSON", columnDefinition = "LONGTEXT")
    private String json;

    @Column(name = "PERSONAL_INFO", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "join_personalInfo",
    joinColumns = {
            @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PERSONAL_INFO_ID")
    })
    private PersonalInfo personalInfo;

}
