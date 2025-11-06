package com.swe.project.services.patientService;

import com.swe.project.entities.patients.Patients;
import com.swe.project.models.patient.PatientRegistrationDto;
import com.swe.project.models.patient.PatientRegistrationResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                .build();
    }
}
