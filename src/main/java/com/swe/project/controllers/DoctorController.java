package com.swe.project.controllers;

import com.swe.project.entities.Doctors;
import com.swe.project.models.AuthRequest;
import com.swe.project.models.FindDoctorByDepartment;
import com.swe.project.services.user.DoctorService;
import com.swe.project.services.user.UserService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("doctor")
    public ResponseEntity<List<Doctors>> authenticate(@RequestBody FindDoctorByDepartment request){
        return ResponseEntity.ok(doctorService.getDoctorsByDepartmentId(request.getDepartmentId()));
    }
}
