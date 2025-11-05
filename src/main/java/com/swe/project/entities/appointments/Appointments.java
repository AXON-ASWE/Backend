package com.swe.project.entities.appointments;

import com.swe.project.entities.doctors.Doctors;
import com.swe.project.entities.patients.Patients;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_APPOINTMENT"
        )
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID")
    private Integer id;

    /**
     * Many appointments belong to one patient.
     */
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID", nullable = false)
    private Patients patient;


    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCTOR_ID", nullable = false)
    private Doctors doctor;

    @Column(name = "APPOINTMENT_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate appointmentDate;

    @Column(name = "TIME_SLOT")
    private int timeSlot;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "NOTES", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "CREATED_AT", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}