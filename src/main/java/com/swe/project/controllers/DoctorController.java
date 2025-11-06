package com.swe.project.controllers;

import com.swe.project.models.CreateDoctorRequest;
import com.swe.project.models.CreateDoctorResponse;
import com.swe.project.models.DoctorResponse;
import com.swe.project.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("doctor")
    public ResponseEntity<List<DoctorResponse>> getDoctor(@RequestParam Integer departmentId) {
        return ResponseEntity.ok(doctorService.getDoctorsByDepartmentId(departmentId));
    }

    @PostMapping("doctor/create")
    public ResponseEntity<CreateDoctorResponse> createDoctorProfile(@RequestBody CreateDoctorRequest request) {
        CreateDoctorResponse response = doctorService.createDoctor(request,passwordEncoder);
        // error
        if (!response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 201 Created
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
