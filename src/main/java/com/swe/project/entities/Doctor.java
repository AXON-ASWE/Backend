package com.swe.project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true) // Important when extending entities
@NoArgsConstructor
@Entity
@Table(name = "T_DOCTOR")
@DiscriminatorValue("DOCTOR") // Value stored in the T_USER.ROLE column for doctors
public class Doctor extends User {

    @Column(name = "SPECIALIZATION")
    private String specialization;

    @Column(name = "EXPERIENCE_YEARS")
    private Integer experienceYears;

    @Column(name = "QUALIFICATION")
    private String qualification;

    // Mapping work shifts
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "T_DOCTOR_WORK_SHIFTS", joinColumns = @JoinColumn(name = "USER_ID"))
    private List<WorkShift> workShifts = new ArrayList<>();

    // Mapping appointment
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
}