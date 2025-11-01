package com.swe.project.controllers;

import com.swe.project.models.DoctorResponse;
import com.swe.project.models.FindDoctorByDepartment;
import com.swe.project.models.createDoctorsRequest;
import com.swe.project.models.createDoctorsResponse;
import com.swe.project.services.user.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("doctor")
    public ResponseEntity<List<DoctorResponse>> getDoctor(@RequestBody FindDoctorByDepartment request){
        return ResponseEntity.ok(doctorService.getDoctorsByDepartmentId(request.getDepartmentId()));
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
