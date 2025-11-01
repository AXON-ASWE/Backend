package com.swe.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_SYMPTOM")
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SYMPTOM_ID")
    private Integer id;


    @Column(name = "SYMPTOM_NAME", nullable = false)
    private String symptomName;

    @Column(nullable = true)
    private String description;

    // A no-argument constructor is required by JPA


    // --- Getters and Setters ---
    public Integer getId() {
        return id;
    }
    @Column(name = "TAG")
    private String tag;

    public void setId(Integer id) {
        this.id = id;
    }
    @ManyToMany(mappedBy = "symptoms", fetch = FetchType.LAZY)
    private List<Departments> departments = new ArrayList<>();
}


