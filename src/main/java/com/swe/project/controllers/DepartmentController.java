package com.swe.project.controllers;

import com.swe.project.models.DepartmentSuggestionDTO;
import com.swe.project.models.DoctorResponse;
import com.swe.project.models.ListSymptomId;
import com.swe.project.services.DepartmentService;
import com.swe.project.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DoctorService doctorService;

    @PostMapping("department/suggest")
    public ResponseEntity<List<DepartmentSuggestionDTO>> suggestDepartment(@RequestBody ListSymptomId request) {
        return ResponseEntity.ok(departmentService.listMatchDepartment(request.getSymptomIds()));
    }
    @GetMapping("doctor")
    public ResponseEntity<List<DoctorResponse>> getDoctor(@RequestParam Integer departmentId) {
        return ResponseEntity.ok(doctorService.getDoctorsByDepartmentId(departmentId));
    }
}
