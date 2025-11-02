package com.swe.project.controllers;

import com.swe.project.entities.Appointments;
import com.swe.project.models.AppointmentResponseDTO;
import com.swe.project.models.CreateAppointmentRequestDTO;
import com.swe.project.models.TimeSlotResponse;
import com.swe.project.services.user.AppointmentService;
import com.swe.project.services.user.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/available")
    public ResponseEntity<TimeSlotResponse> getAvailableAppointment(
            @RequestParam Integer doctorId,
            @RequestParam Integer patientId,
            @RequestParam LocalDate date
    ){
        return ResponseEntity.ok(appointmentService.getListOfAvailableAppointment(doctorId,patientId,date));
    }
    @PostMapping("/create")
    public ResponseEntity<AppointmentResponseDTO> createAppointment(
            @RequestBody CreateAppointmentRequestDTO  request
            ){
        Appointments appointment = appointmentService.createAppointment(request.getPatientId(),request.getDoctorId(),request.getDate(),request.getTimeSlot(),request.getNotes());
        AppointmentResponseDTO response = new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getDoctor().getDoctorName(),
                appointment.getPatient().getUser().getUsername(),
                appointment.getAppointmentDate(),
                appointment.getTimeSlot(),
                appointment.getStatus()
        );
        return ResponseEntity.status(201).body(response);
    }
    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelAppointment(@PathVariable Integer id) {
        boolean success = appointmentService.cancelAppointment(id);
        if (success) {
            return ResponseEntity.ok("Appointment cancelled successfully");
        } else {
            return ResponseEntity.badRequest().body("Unable to cancel appointment — either not found or not in SCHEDULED state");
        }
    }
}
