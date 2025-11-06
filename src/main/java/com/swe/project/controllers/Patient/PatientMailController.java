package com.swe.project.controllers.Patient;

import com.swe.project.models.authentication.AuthenticationResponse;
import com.swe.project.services.mailService.EmailVerificationService;
import com.swe.project.services.patientService.PatientAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient/mail")
@RequiredArgsConstructor
public class PatientMailController {

    private final EmailVerificationService emailVerificationService;
    private final PatientAuthenticationService patientAuthenticationService;
    /**
     * Xác nhận email bằng token
     */
    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        boolean isVerified = emailVerificationService.verifyEmail(token);

        if (isVerified) {
            AuthenticationResponse authResponse = patientAuthenticationService.activatePatientAccount(
                    emailVerificationService.getEmailFromToken(token)
            );

            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "http://localhost:3000/patient/login?token=" + authResponse.getAccessToken() + "&refreshToken=" + authResponse.getRefreshToken())
                    .body("Xác minh email thành công. Bạn sẽ được chuyển hướng đến trang đăng nhập.");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Xác minh email thất bại. Vui lòng thử lại.");
        }
    }
}
