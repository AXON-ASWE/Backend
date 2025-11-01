package com.swe.project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "T_PATIENT")
public class Patients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Maps the N-to-1 relationship from Patient to User.
     * Many patient profiles can be managed by one user account.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "ADDRESS")
    private String address;

    @Lob // For large text
    @Column(name = "MEDICAL_HISTORY")
    private String medicalHistory;

    @Column(name = "EMERGENCY_CONTACT")
    private String emergencyContact;

    @Column(name = "INSURANCE_NUMBER")
    private String insuranceNumber;

    /**
     * Maps the 1-to-N "Has" relationship.
     * One patient can have many appointments.
     */
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointments> appointments = new ArrayList<>();
}
