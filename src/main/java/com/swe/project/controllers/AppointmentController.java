package com.swe.project.controllers;

import com.swe.project.entities.appointments.Appointments;
import com.swe.project.models.*;
import com.swe.project.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/available")
    public ResponseEntity<TimeSlotResponse> getAvailableAppointment(
            @RequestParam Integer doctorId,
            @RequestParam LocalDate date
    ) {
        Integer userId = ((Number) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()).intValue();
        String role =  (String) SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);
        if(!role.equals("ROLE_PATIENT")){
            return ResponseEntity.status(401).build();
        }


        return ResponseEntity.ok(appointmentService.getListOfAvailableAppointment(doctorId, userId, date));
    }

    @GetMapping("/patient")
    public ResponseEntity<List<PatientAppointmentResponseDTO>> getPatientAppointment() {
        Integer userId = ((Number) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()).intValue();
        String role =  (String) SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);
        if(!role.equals("ROLE_PATIENT")){
            return ResponseEntity.status(401).build();
        }
        List<PatientAppointmentResponseDTO> listPatientAppointments = appointmentService.getListPatientAppointments(userId);
        return ResponseEntity.ok(listPatientAppointments);
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<DoctorAppointmentResponseDTO>> getDoctorAppointment() {
        Integer userId = ((Number) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()).intValue();
        String role =  (String) SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);
        if(!role.equals("ROLE_DOCTOR")){
            return ResponseEntity.status(401).build();
        }
        List<DoctorAppointmentResponseDTO> listPatientAppointments = appointmentService.getListDoctorAppointments(userId);
        return ResponseEntity.ok(listPatientAppointments);
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@RequestBody CreateAppointmentRequestDTO request) {
        Integer userId = ((Number) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()).intValue();
        String role =  (String) SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);
        if(!role.equals("ROLE_PATIENT")){
            return ResponseEntity.status(401).build();
        }
        Appointments appointment = appointmentService.createAppointment(
                userId,
                request.getDoctorId(),
                request.getDate(),
                request.getTimeSlot(),
                request.getNotes()
        );
        AppointmentResponseDTO response = new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getDoctor().getUser().getFullName(),
                appointment.getPatient().getUser().getFullName(),
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

    @PutMapping("/{id}/reschedule")
    public ResponseEntity<String> rescheduleAppointment(@PathVariable Integer id, @RequestBody RescheduleAppointmentRequest request) {
        boolean success = appointmentService.rescheduleAppointment(id, request.getAppointmentDate(), request.getTimeSlot());
        if (success) {
            return ResponseEntity.ok("Appointment rescheduled successfully");
        } else {
            return ResponseEntity.badRequest().body("Unable to rescheduled appointment");
        }
    }
}
