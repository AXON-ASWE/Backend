package com.swe.project.repositories;

import com.swe.project.entities.appointments.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments,Integer> {
    @Query(value = """
    SELECT time_slot
    FROM t_appointment
    WHERE (patient_id = :patientId)
    AND appointment_date = :appointmentDate
    and (status = 'COMPLETED' OR status = 'SCHEDULED')
""", nativeQuery = true)
    List<Integer> getListOfPatientScheduledAppointment(@Param("patientId") Integer patientId, @Param("appointmentDate") LocalDate appointmentDate);
    @Query(value = """
    SELECT time_slot
    FROM t_appointment
    WHERE (doctor_id = :doctorId)
    AND appointment_date = :appointmentDate
    and (status = 'COMPLETED' OR status = 'SCHEDULED')
""", nativeQuery = true)
    List<Integer> getListOfDoctorScheduledAppointment(@Param("doctorId") Integer doctorId, @Param("appointmentDate") LocalDate appointmentDate);


    @Modifying
    @Transactional
    @Query("UPDATE Appointments a SET a.status = 'CANCELLED' WHERE a.id = :appointmentId")
    int cancelAppointment(Integer appointmentId);

    @Modifying
    @Transactional
    @Query("""
    UPDATE Appointments a 
    SET a.appointmentDate = :appointmentDate,
        a.timeSlot = :timeSlot,
        a.createdAt = :createdAt
    WHERE a.id = :appointmentId
""")
    int updateAppointment(
            @Param("appointmentId") Integer appointmentId,
            @Param("appointmentDate") LocalDate appointmentDate,
            @Param("timeSlot") int timeSlot,
            @Param("createdAt") LocalDateTime createdAt
    );

    List<Appointments> findByPatient_User_UserId(Integer userId);
    List<Appointments> findByDoctor_User_UserId(Integer userId);
}
