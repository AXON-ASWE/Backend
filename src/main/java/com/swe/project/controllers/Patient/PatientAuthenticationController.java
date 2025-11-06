package com.swe.project.controllers.Patient;

import com.swe.project.models.authentication.AuthenticationResponse;
import com.swe.project.models.patient.PatientRegistrationDto;
import com.swe.project.services.patient.PatientAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient/auth")
@RequiredArgsConstructor
public class PatientAuthenticationController {

    private final PatientAuthenticationService patientAuthenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerPatient(@RequestBody PatientRegistrationDto registrationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                patientAuthenticationService.registerPatient(registrationDto)
        );
    }

}
