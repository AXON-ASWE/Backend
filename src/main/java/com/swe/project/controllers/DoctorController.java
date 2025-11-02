package com.swe.project.controllers;

import com.swe.project.entities.Doctors;
import com.swe.project.models.DoctorResponse;
import com.swe.project.models.FindDoctorByDepartment;
import com.swe.project.models.createDoctorsRequest;
import com.swe.project.models.createDoctorsResponse;
import com.swe.project.services.user.DoctorService;
import com.swe.project.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("doctor")
    public ResponseEntity<List<DoctorResponse>> getDoctor(@RequestParam Integer departmentId){
        return ResponseEntity.ok(doctorService.getDoctorsByDepartmentId(departmentId));
    }

    @PostMapping("doctor/create")
    public ResponseEntity<createDoctorsResponse> createDoctorProfile(
            @RequestBody createDoctorsRequest request
    ) {
        createDoctorsResponse response = doctorService.createDoctor(request);
        // error
        if (!response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 201 Created
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
