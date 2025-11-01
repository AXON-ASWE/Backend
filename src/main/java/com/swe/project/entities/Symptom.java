package com.swe.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "T_SYMPTOM")
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String symptomName;

    @Column(nullable = true)
    private String description;

    // A no-argument constructor is required by JPA
    public Symptom() {
    }

    // --- Getters and Setters ---
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }

    @JsonIgnore
    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }
}