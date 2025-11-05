package com.swe.project.services;

import com.swe.project.entities.appointments.Appointments;
import com.swe.project.entities.doctors.Doctors;
import com.swe.project.entities.patients.Patients;
import com.swe.project.models.DoctorAppointmentResponseDTO;
import com.swe.project.models.PatientAppointmentResponseDTO;
import com.swe.project.models.TimeSlotResponse;
import com.swe.project.repositories.AppointmentRepository;
import com.swe.project.repositories.DoctorRepository;
import com.swe.project.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public TimeSlotResponse getListOfAvailableAppointment(Integer doctorId, Integer patientId, LocalDate date) {
        List<Integer> allSlots = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            allSlots.add(i);
        }
        List<Integer> listScheduledPatientAppointments = appointmentRepository.getListOfPatientScheduledAppointment(patientId, date);
        List<Integer> listScheduledDoctorAppointments = appointmentRepository.getListOfDoctorScheduledAppointment(doctorId, date);
        allSlots.removeAll(listScheduledDoctorAppointments);
        allSlots.removeAll(listScheduledPatientAppointments);
        
        TimeSlotResponse timeSlotResponse = new TimeSlotResponse();
        timeSlotResponse.setListOfAvailableTimeSlots(allSlots);
        return timeSlotResponse;
    }

    public Appointments createAppointment(Integer patientId, Integer doctorId, LocalDate date, int timeSlot, String notes) {
        Optional<Doctors> doctorOpt = doctorRepository.findById(doctorId);
        Optional<Patients> patientOpt = patientRepository.findById(patientId);

        if (doctorOpt.isEmpty() || patientOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid doctorId or patientId");
        }
        
        Doctors doctor = doctorOpt.get();
        Patients patient = patientOpt.get();
        Appointments appointment = new Appointments();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(date);
        appointment.setTimeSlot(timeSlot);
        appointment.setStatus("SCHEDULED");
        appointment.setNotes(notes);

        return appointmentRepository.save(appointment);
    }

    public boolean cancelAppointment(Integer appointmentId) {
        int updated = appointmentRepository.cancelAppointment(appointmentId);
        return updated > 0;
    }

    public boolean rescheduleAppointment(Integer appointmentId, LocalDate appointmentDate, int timeSlot) {
        int rescheduled = appointmentRepository.updateAppointment(appointmentId, appointmentDate, timeSlot, LocalDateTime.now());
        return rescheduled > 0;
    }

    public List<PatientAppointmentResponseDTO> getListPatientAppointments(Integer patientId) {
        Optional<Patients> patientOpt = patientRepository.findById(patientId);

        if (patientOpt.isEmpty()) {
            return null;
        }
        
        List<Appointments> listPatientAppointment = appointmentRepository.findByPatient_UserId(patientId);
        return listPatientAppointment.stream()
                .map(PatientAppointmentResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<DoctorAppointmentResponseDTO> getListDoctorAppointments(Integer doctorId) {
        Optional<Doctors> doctorOpt = doctorRepository.findById(doctorId);

        if (doctorOpt.isEmpty()) {
            return null;
        }
        
        List<Appointments> listPatientAppointment = appointmentRepository.findByDoctor_UserId(doctorId);
        return listPatientAppointment.stream()
                .map(DoctorAppointmentResponseDTO::new)
                .collect(Collectors.toList());
    }
}
