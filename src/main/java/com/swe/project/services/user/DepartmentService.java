package com.swe.project.services.user;

import com.swe.project.models.DepartmentSuggestionDTO;
import com.swe.project.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public List<DepartmentSuggestionDTO>  listMatchDepartment(List<Integer> SymptomIds){
        return departmentRepository.findDepartmentsByMostMatchingSymptoms(SymptomIds);
    }
}
