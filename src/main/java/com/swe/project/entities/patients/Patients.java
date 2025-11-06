package com.swe.project.entities.patients;

import com.swe.project.entities.appointments.Appointments;
import com.swe.project.entities.users.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_PATIENTS")
@SuperBuilder
public class Patients extends Users{
    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMERGENCY_CONTACT")
    private String emergencyContact;

    @Column(name = "INSURANCE_NUMBER")
    private String insuranceNumber;

    @OneToMany(mappedBy = "patient")
    private List<Appointments> appointments;

}
