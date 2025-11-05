package com.swe.project.controllers;

import com.swe.project.models.DepartmentSuggestionDTO;
import com.swe.project.models.ListSymptomId;
import com.swe.project.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("department/suggest")
    public ResponseEntity<List<DepartmentSuggestionDTO>> suggestDepartment(@RequestBody ListSymptomId request) {
        return ResponseEntity.ok(departmentService.listMatchDepartment(request.getSymptomIds()));
    }
}
