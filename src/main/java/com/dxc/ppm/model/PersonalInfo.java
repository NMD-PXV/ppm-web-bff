package com.dxc.ppm.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "personalInfo")
public class PersonalInfo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "DOB", nullable = false)
    private Date dob;

    @Column(name = "POD", nullable = false)
    private String pob;

    @Column(name = "SEX", length = 1, nullable = false)
    private String sex;

    @OneToOne(mappedBy = "personalInfo")
    private Patient patient;
}
