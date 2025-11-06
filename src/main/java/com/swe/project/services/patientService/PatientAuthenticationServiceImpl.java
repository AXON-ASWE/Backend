package com.swe.project.services.patientService;

import com.swe.project.entities.patients.Patients;
import com.swe.project.exception.UserNotFoundException;
import com.swe.project.exception.UserRegistrationException;
import com.swe.project.exception.UserWrongPasswordException;
import com.swe.project.models.authentication.AuthenticationResponse;
import com.swe.project.models.patient.PatientRegistrationDto;
import com.swe.project.repositories.PatientRepository;
import com.swe.project.services.mailService.EmailVerificationService;
import com.swe.project.utils.JwtUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientAuthenticationServiceImpl implements PatientAuthenticationService{

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final PatientRepository patientRepository;
    private final EmailVerificationService emailVerificationService;
    private final Logger logger = LoggerFactory.getLogger(PatientAuthenticationServiceImpl.class);

    @Override
    public void registerPatient(PatientRegistrationDto registrationDto) {

        Patients patients = patientRepository.findByEmailAndStatus(
                registrationDto.getEmail(),
                "ACTIVE"
        ).orElse(null);

        if (patients != null) {
            throw new UserRegistrationException(registrationDto.getEmail());
        }

        Patients newPatient = PatientUtils.toEntity(registrationDto, passwordEncoder);

        newPatient = patientRepository.save(newPatient);

        try {
            emailVerificationService.sendVerificationEmail(newPatient.getEmail(), newPatient.getFullName());
            logger.info("Đã gởi mail xác nhận đến: {}", newPatient.getEmail());
        } catch (MessagingException e) {
            logger.error("Không thể gởi mail xác nhận đến {}: {}", newPatient.getEmail(), e.getMessage());
            throw new RuntimeException("Gửi email xác nhận thất bại: " + e.getMessage());
        }

    }

    @Override
    public AuthenticationResponse activatePatientAccount(String email) {
        Patients patients = patientRepository.findByEmailAndStatus(
                email,
                "INACTIVE"
        ).orElseThrow(
                UserNotFoundException::new
        );

        patients.setStatus("ACTIVE");
        patientRepository.save(patients);

        String accessToken = jwtUtil.generateToken(
                patients.getUserId(),
                patients.getEmail(),
                patients.getRole(),
                false
        );

        String refreshToken = jwtUtil.generateToken(
                patients.getUserId(),
                patients.getEmail(),
                patients.getRole(),
                true
        );

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse loginPatient(String email, String password) {
        Patients patients = patientRepository.findByEmailAndStatus(
                email,
                "ACTIVE"
        ).orElseThrow(
                UserNotFoundException::new
        );

        if (!passwordEncoder.matches(password, patients.getPasswordHash())) {
            throw new UserWrongPasswordException();
        }

        String accessToken = jwtUtil.generateToken(
                patients.getUserId(),
                patients.getEmail(),
                patients.getRole(),
                false
        );

        String refreshToken = jwtUtil.generateToken(
                patients.getUserId(),
                patients.getEmail(),
                patients.getRole(),
                true
        );

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
