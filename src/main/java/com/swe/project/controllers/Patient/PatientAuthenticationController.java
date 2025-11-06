package com.swe.project.controllers.Patient;

import com.swe.project.models.patient.PatientRegistrationDto;
import com.swe.project.services.patientService.PatientAuthenticationService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> registerPatient(@RequestBody PatientRegistrationDto registrationDto) {

        patientAuthenticationService.registerPatient(registrationDto);

        return ResponseEntity.ok(
            "Đăng kí thành công, vui lòng kiểm tra email để xác nhận tài khoản."
        );
    }
}
