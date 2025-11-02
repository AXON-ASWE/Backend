package com.swe.project.models;

import java.time.LocalDate;

public record AppointmentResponseDTO(
        Integer id,
        String doctorName,
        String patientName,
        LocalDate appointmentDate,
        int timeSlot,
        String status
) {
}
