package com.swe.project.services.patientService;

import com.swe.project.entities.patients.Patients;
import com.swe.project.models.patient.PatientRegistrationDto;
import com.swe.project.models.patient.PatientRegistrationResponseDto;
import com.swe.project.repositories.PatientRepository;
import com.swe.project.utils.JwtUtil;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientAuthenticationServiceImpl implements PatientAuthenticationService{

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final PatientRepository patientRepository;

    @Override
    public PatientRegistrationResponseDto registerPatient(PatientRegistrationDto registrationDto) {

        Patients newPatient = PatientUtils.toEntity(registrationDto, passwordEncoder);
        newPatient = patientRepository.save(newPatient);
        String accessToken = jwtUtil.generateToken(newPatient.getUserId(), newPatient.getEmail(), newPatient.getRole(), false);
        String refreshToken = jwtUtil.generateToken(newPatient.getUserId(), newPatient.getEmail(), newPatient.getRole(), true);
        return PatientRegistrationResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }
}
