package com.swe.project.services.admin;

import com.swe.project.entities.departments.Departments;
import com.swe.project.entities.patients.Patients;
import com.swe.project.models.admin.CreateDepartmentRequest;
import com.swe.project.models.admin.DepartmentResponseDTO;
import com.swe.project.models.admin.UpdateDepartmentRequest;
import com.swe.project.repositories.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springdoc.webmvc.core.service.RequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminDepartmentService {
    private final DepartmentRepository departmentRepository;
    private final RequestService requestService;

    public AdminDepartmentService(DepartmentRepository departmentRepository, RequestService requestService)
    {
        this.departmentRepository = departmentRepository;
        this.requestService = requestService;
    }
    public DepartmentResponseDTO createDepartment(CreateDepartmentRequest createDepartmentRequest){
        Departments newDepartment = new Departments();
        newDepartment.setDepartmentName(createDepartmentRequest.getDepartmentName());
        newDepartment.setDescription(createDepartmentRequest.getDepartmentDescription());
        newDepartment.setLocation(createDepartmentRequest.getDepartmentLocation());
        Departments savedDepartment = departmentRepository.save(newDepartment);
        return convertToDto(savedDepartment);
    }
    public List<DepartmentResponseDTO> getAllDepartments()
    {
        return departmentRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public DepartmentResponseDTO getDepartmentById(Integer departmentId)
    {
        return convertToDto(departmentRepository.findById(departmentId).orElse(null));
    }
    public DepartmentResponseDTO updateDepartmentById(Integer departmentId,UpdateDepartmentRequest UpdateDepartmentRequest){
        Departments department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khoa với ID: " + departmentId));
        if(UpdateDepartmentRequest.getDepartmentName()!=null){
            department.setDepartmentName(UpdateDepartmentRequest.getDepartmentName());
        }
        if(UpdateDepartmentRequest.getDepartmentDescription()!=null){
            department.setDescription(UpdateDepartmentRequest.getDepartmentDescription());
        }
        if (UpdateDepartmentRequest.getDepartmentLocation()!=null){
            department.setLocation(UpdateDepartmentRequest.getDepartmentLocation());
        }
        departmentRepository.save(department);
        return convertToDto(department);
    }
    private  DepartmentResponseDTO convertToDto(Departments department){
        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        departmentResponseDTO.setDepartmentName(department.getDepartmentName());
        departmentResponseDTO.setDepartmentId(department.getId());
        departmentResponseDTO.setDepartmentDescription(department.getDescription());
        departmentResponseDTO.setDepartmentLocation(department.getLocation());
        return departmentResponseDTO;
    }
}
