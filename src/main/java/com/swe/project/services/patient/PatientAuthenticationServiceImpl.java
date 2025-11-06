package com.swe.project.services.patient;

import com.swe.project.entities.patients.Patients;
import com.swe.project.exception.UserRegistrationException;
import com.swe.project.models.authentication.AuthenticationResponse;
import com.swe.project.models.patient.PatientRegistrationDto;
import com.swe.project.repositories.PatientRepository;
import com.swe.project.services.authentication.AuthenticationService;
import com.swe.project.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientAuthenticationServiceImpl implements PatientAuthenticationService{

    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final AuthenticationService authenticationService;

    @Override
    public AuthenticationResponse registerPatient(PatientRegistrationDto registrationDto) {

        Patients patients = patientRepository.findByEmailAndStatus(
                registrationDto.getEmail(),
                "ACTIVE"
        ).orElse(null);

        if (patients != null) {
            throw new UserRegistrationException(registrationDto.getEmail());
        }
        Patients newPatient = PatientUtils.toEntity(registrationDto, passwordEncoder);
        patientRepository.save(newPatient);

        return authenticationService.generateResponse(newPatient.getUser());
    }
}
