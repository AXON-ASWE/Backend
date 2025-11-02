package com.swe.project.models;

import com.swe.project.entities.Appointments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAppointmentResponseDTO {
    private Integer appointmentId;
    private int timeSlot;
    private String status;
    private LocalDate appointmentDate;
    public DoctorAppointmentResponseDTO(Appointments appointments) {
        this.appointmentId = appointments.getId();
        this.timeSlot = appointments.getTimeSlot();
        this.status = appointments.getStatus();
        this.appointmentDate = appointments.getAppointmentDate();
    }
}
