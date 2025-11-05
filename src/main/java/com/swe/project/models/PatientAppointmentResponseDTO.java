package com.swe.project.models;

import com.swe.project.entities.appointments.Appointments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientAppointmentResponseDTO {
    private Integer appointmentId;
    private String doctorName;
    private LocalDate appointmentDate;
    private String departmentName;
    private int timeSlot;
    private String status;
    public PatientAppointmentResponseDTO(Appointments appointment) {
        appointmentId = appointment.getId();
        doctorName = appointment.getDoctor().getFullName();
        appointmentDate = appointment.getAppointmentDate();
        departmentName = appointment.getDoctor().getDepartment().getDepartmentName();
        status = appointment.getStatus();
        timeSlot = appointment.getTimeSlot();
    }
}
