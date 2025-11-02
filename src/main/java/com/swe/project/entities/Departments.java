package com.swe.project.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "T_DEPARTMENT")
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DEPARTMENT_NAME", nullable = false)
    private String departmentName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LOCATION")
    private String location;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "T_DEPARTMENT_SYMPTOM",
            joinColumns = @JoinColumn(name = "department_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id", referencedColumnName="symptom_id")
    )
    private List<Symptom> symptoms = new ArrayList<>();
}
