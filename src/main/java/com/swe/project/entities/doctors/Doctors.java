package com.swe.project.entities.doctors;

import com.swe.project.entities.appointments.Appointments;
import com.swe.project.entities.departments.Departments;
import com.swe.project.entities.users.Users;
import com.swe.project.entities.workshifts.WorkShift;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_DOCTOR")
@Builder
public class Doctors {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOCTOR_ID")
    private Integer doctorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private Users user;

    @Column(name = "SPECIALIZATION", nullable = false)
    private String specialization;

    @Column(name = "EXPERIENCE_YEARS")
    private Integer experienceYears;

    @Column(name = "QUALIFICATIONS")
    private String qualifications;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Appointments> appointments;

    @ElementCollection
    @CollectionTable(name = "T_DOCTOR_WORKSHIFT", joinColumns = @JoinColumn(name = "DOCTOR_ID"))
    @Builder.Default
    private List<WorkShift> workShifts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
    private Departments department;

}
