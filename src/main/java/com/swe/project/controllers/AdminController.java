package com.swe.project.controllers;

import com.swe.project.models.authentication.AdminRegistrationRequest;
import com.swe.project.models.authentication.AuthenticationResponse;
import com.swe.project.services.authentication.AdminRegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminRegistrationService adminRegistrationService;

    public AdminController(AdminRegistrationService adminRegistrationService) {
        this.adminRegistrationService = adminRegistrationService;
    }


    // Đăng ký admin lần đầu (di chuyển từ AuthenticationController)
    @PostMapping("/register-admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody @Valid AdminRegistrationRequest request) {
        return ResponseEntity.ok(adminRegistrationService.register(request));
    }
}
