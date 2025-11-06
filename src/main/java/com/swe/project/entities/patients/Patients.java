package com.swe.project.entities.patients;

import com.swe.project.entities.appointments.Appointments;
import com.swe.project.entities.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_PATIENT")
@Builder
public class Patients {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATIENT_ID")
    private Integer patientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private Users user;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMERGENCY_CONTACT")
    private String emergencyContact;

    @Column(name = "INSURANCE_NUMBER")
    private String insuranceNumber;

    @OneToMany(mappedBy = "patient")
    private List<Appointments> appointments;

}
