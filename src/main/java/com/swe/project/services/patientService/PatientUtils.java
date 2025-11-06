package com.swe.project.services.patientService;

import com.swe.project.entities.patients.Patients;
import com.swe.project.entities.users.RoleName;
import com.swe.project.models.patient.PatientRegistrationDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

public class PatientUtils {

    public static Patients toEntity(PatientRegistrationDto dto, PasswordEncoder passwordEncoder) {
        return Patients.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .passwordHash(
                        (passwordEncoder != null)
                                ? passwordEncoder.encode(dto.getPassword())
                                : dto.getPassword()
                )
                .role(RoleName.PATIENT)
                .createdAt(LocalDate.now())
                .status("ACTIVE")
                .build();
    }
}
