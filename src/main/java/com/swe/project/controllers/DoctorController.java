package com.swe.project.controllers;

import com.swe.project.models.CreateDoctorRequest;
import com.swe.project.models.CreateDoctorResponse;
import com.swe.project.models.DoctorResponse;
import com.swe.project.services.DoctorService;
import com.swe.project.models.UpdateDoctorRequest;
import lombok.RequiredArgsConstructor;
import com.swe.project.models.DoctorDetailResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final PasswordEncoder passwordEncoder;
//    @GetMapping("doctor")
//    public ResponseEntity<List<DoctorResponse>> getDoctor(@RequestParam Integer departmentId) {
//        return ResponseEntity.ok(doctorService.getDoctorsByDepartmentId(departmentId));
//    }

    @PostMapping("doctor")
    public ResponseEntity<CreateDoctorResponse> createDoctorProfile(@RequestBody CreateDoctorRequest request) {
        CreateDoctorResponse response = doctorService.createDoctor(request,passwordEncoder);
        // error
        if (!response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 201 Created
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all doctors", description = "Retrieves a list of all doctors in the system.")
    @GetMapping("/doctor")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }


    @Operation(summary = "Get a doctor by ID", description = "Retrieves the full details of a single doctor by their ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved doctor details"),
            @ApiResponse(responseCode = "404", description = "Doctor not found with the provided ID")
    })
    @GetMapping("/doctor/{id}")
    public ResponseEntity<DoctorDetailResponse> getDoctorById(
            @Parameter(description = "The ID of the doctor to retrieve", required = true)
            @PathVariable Integer id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }


    @Operation(summary = "Update a doctor's profile", description = "Updates the details of an existing doctor by their ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Doctor profile updated successfully"),
            @ApiResponse(responseCode = "404", description = "Doctor not found with the provided ID")
    })
    @PutMapping("/doctor/{id}")
    public ResponseEntity<DoctorDetailResponse> updateDoctor(
            @Parameter(description = "The ID of the doctor to update", required = true)
            @PathVariable Integer id,
            @RequestBody UpdateDoctorRequest request) {
        DoctorDetailResponse updatedDoctor = doctorService.updateDoctor(id, request);
        return ResponseEntity.ok(updatedDoctor);
    }


    @Operation(summary = "Deactivate a doctor's profile (Soft Delete)", description = "Changes the status of the doctor's associated user account to INACTIVE.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Doctor deactivated successfully"),
            @ApiResponse(responseCode = "404", description = "Doctor not found with the provided ID")
    })
    @DeleteMapping("/doctor/{id}")
    public ResponseEntity<Void> deactivateDoctor(
            @Parameter(description = "The ID of the doctor to deactivate", required = true)
            @PathVariable Integer id) {
        doctorService.deactivateDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
