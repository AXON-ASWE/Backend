package com.swe.project.services;

import com.swe.project.models.DepartmentSuggestionDTO;
import com.swe.project.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<DepartmentSuggestionDTO> listMatchDepartment(List<Integer> symptomIds) {
        return departmentRepository.findDepartmentsByMostMatchingSymptoms(symptomIds);
    }
}
