package com.swe.project.entities.symptoms;

import com.swe.project.entities.departments.Departments;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Column(name = "TAG")
    private String tag;

    @ToString.Exclude
    @ManyToMany(mappedBy = "symptoms", fetch = FetchType.LAZY)
    private List<Departments> departments = new ArrayList<>();
}


