package com.swe.project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data

@NoArgsConstructor
@Entity
@Table(name = "T_DEPARTMENT")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID")
    private Integer id;

    @Column(name = "DEPARTMENT_NAME", unique = true)
    private String departmentName;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LOCATION")
    private String location;

    /**
     * One department has many doctors.
     */
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Doctor> doctors = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "T_DEPARTMENT_SYMPTOMS",
            joinColumns = @JoinColumn(name = "DEPARTMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SYMPTOM_ID")
    )
    private List<Symptom> symptoms = new ArrayList<>();


}
