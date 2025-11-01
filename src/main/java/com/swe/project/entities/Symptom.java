package com.swe.project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_SYMPTOM")
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SYMPTOM_ID")
    private Integer id;

    @Column(name = "SYMPTOM_NAME", nullable = false)
    private String symptomName;

    @Column(name = "TAG")
    private String tag;

    @ManyToMany(mappedBy = "symptoms", fetch = FetchType.LAZY)
    private List<Departments> departments = new ArrayList<>();
}
