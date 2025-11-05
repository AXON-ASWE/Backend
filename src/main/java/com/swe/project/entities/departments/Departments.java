package com.swe.project.entities.departments;

import com.swe.project.entities.doctors.Doctors;
import com.swe.project.entities.symptoms.Symptom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "T_DEPARTMENT")
@AllArgsConstructor
@NoArgsConstructor
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID", nullable = false)
    private Integer id;

    @Column(name = "DEPARTMENT_NAME", nullable = false)
    private String departmentName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LOCATION")
    private String location;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "T_DEPARTMENT_SYMPTOM",
            joinColumns = @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName="DEPARTMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SYMPTOM_ID", referencedColumnName="SYMPTOM_ID")
    )
    private List<Symptom> symptoms = new ArrayList<>();

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Doctors> doctors = new ArrayList<>();

}
