package com.swe.project.controllers;

import com.swe.project.models.admin.PatientResponseDTO;
import com.swe.project.models.admin.UpdatePatientRequest;
import com.swe.project.models.authentication.AdminRegistrationRequest;
import com.swe.project.models.authentication.AuthenticationResponse;
import com.swe.project.services.admin.AdminPatientService;
import com.swe.project.services.authentication.AdminRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin", description = "API quản trị hệ thống")
public class AdminController {

    private final AdminRegistrationService adminRegistrationService;
    private final AdminPatientService adminPatientService;

    public AdminController(AdminRegistrationService adminRegistrationService,
                           AdminPatientService adminPatientService) {
        this.adminRegistrationService = adminRegistrationService;
        this.adminPatientService = adminPatientService;
    }

    @GetMapping("/ping")
    @Operation(summary = "Ping test cho admin")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("admin ok");
    }

    // ==================== ADMIN REGISTRATION ====================
    
    @PostMapping("/register-admin")
    @Operation(summary = "Đăng ký admin lần đầu")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody @Valid AdminRegistrationRequest request) {
        return ResponseEntity.ok(adminRegistrationService.register(request));
    }

    // ==================== PATIENT MANAGEMENT ====================
    
    @GetMapping("/patient")
    @Operation(summary = "Lấy danh sách tất cả bệnh nhân")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(adminPatientService.getAllPatients());
    }

    @PutMapping("/patient/{patientId}")
    @Operation(summary = "Cập nhật thông tin bệnh nhân")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable Integer patientId,
            @RequestBody @Valid UpdatePatientRequest request) {
        return ResponseEntity.ok(adminPatientService.updatePatient(patientId, request));
    }

    @DeleteMapping("/patient/{patientId}")
    @Operation(summary = "Xóa bệnh nhân (soft delete)")
    public ResponseEntity<Map<String, Object>> deletePatient(@PathVariable Integer patientId) {
        adminPatientService.deletePatient(patientId);
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("message", "Patient deleted successfully");
        body.put("patientId", patientId);
        return ResponseEntity.ok(body);
    }
}
