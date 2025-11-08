package com.swe.project.controllers;

import com.swe.project.models.DepartmentSuggestionDTO;
import com.swe.project.models.admin.*;
import com.swe.project.models.authentication.AdminRegistrationRequest;
import com.swe.project.models.authentication.AuthenticationResponse;
import com.swe.project.services.admin.AdminDepartmentService;
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

    private final AdminDepartmentService adminDepartmentService;
    public AdminController(AdminRegistrationService adminRegistrationService,
                           AdminPatientService adminPatientService, AdminDepartmentService adminDepartmentService) {
        this.adminRegistrationService = adminRegistrationService;
        this.adminPatientService = adminPatientService;
        this.adminDepartmentService = adminDepartmentService;
    }

    @GetMapping("/ping")
    @Operation(summary = "Ping test cho admin")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("admin ok");
    }

    // ==================== ADMIN REGISTRATION ====================
    
    @PostMapping("/register")
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
    public ResponseEntity<Void> deletePatient(@PathVariable Integer patientId) {
        adminPatientService.deletePatient(patientId);
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentResponseDTO>> getDepartments() {
        return ResponseEntity.ok(adminDepartmentService.getAllDepartments());
    }
    @PostMapping("/department")
    public ResponseEntity<DepartmentResponseDTO> createDepartment(
            @RequestBody CreateDepartmentRequest request
    ) {
        return ResponseEntity.status(201).body(adminDepartmentService.createDepartment(request));
    }
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(
            @PathVariable Integer departmentId
    ) {
        return ResponseEntity.ok(adminDepartmentService.getDepartmentById(departmentId));
    }
    @PutMapping("/department/{departmentId}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartmentById(
            @PathVariable Integer departmentId,
            @RequestBody UpdateDepartmentRequest request
    ) {
        return ResponseEntity.ok(adminDepartmentService.updateDepartmentById(departmentId,request));
    }

}
