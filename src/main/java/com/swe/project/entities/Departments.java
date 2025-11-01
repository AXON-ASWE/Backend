package com.swe.project.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "T_DEPARTMENT_SYMPTOM",
            joinColumns = @JoinColumn(name = "department_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id", referencedColumnName="symptom_id")
    )
    private List<Symptom> symptoms = new ArrayList<>();

    public Departments() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
