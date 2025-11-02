package com.swe.project.services.user;

import com.swe.project.entities.Appointments;
import com.swe.project.entities.Doctors;
import com.swe.project.entities.Patients;
import com.swe.project.models.TimeSlotResponse;
import com.swe.project.repositories.AppointmentRepository;
import com.swe.project.repositories.DoctorRepository;
import com.swe.project.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public TimeSlotResponse getListOfAvailableAppointment(Integer doctorId, Integer patientId, LocalDate date){
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

    public boolean cancelAppointment(Integer appointmentId){
        int updated = appointmentRepository.cancelAppointment(appointmentId);
        return updated > 0;
    }
}
