package com.dxc.ppm.model;

import javax.persistence.*;

@Entity
@Table(name = "GivenMedicine")
public class GivenMedicine {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "AMOUNT")
    private int amount;

}
