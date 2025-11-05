package com.swe.project.services;

import com.swe.project.entities.Departments;
import com.swe.project.entities.Doctors;
import com.swe.project.models.CreateDoctorRequest;
import com.swe.project.models.CreateDoctorResponse;
import com.swe.project.models.DoctorResponse;
import com.swe.project.repositories.DepartmentRepository;
import com.swe.project.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    public List<DoctorResponse> getDoctorsByDepartmentId(Integer departmentId) {
        List<Doctors> doctors = doctorRepository.findByDepartment_Id(departmentId);
        return doctors.stream()
                .map(DoctorResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Business logic to create a new Doctor profile
     */
    @Transactional
    public CreateDoctorResponse createDoctor(CreateDoctorRequest request) {
        // 1. Find the department
        Departments department = departmentRepository.findById(request.getDepartmentId())
                .orElse(null);

        if (department == null) {
            // Return an error response if not found
            return new CreateDoctorResponse(false, "Department not found with ID: " + request.getDepartmentId());
        }

        // 2. Create a new Doctors object (from Doctors.java file)
        Doctors newDoctor = new Doctors();
        newDoctor.setDoctorName(request.getDoctorName());
        newDoctor.setExperience(request.getExperience());
        newDoctor.setDoctorEmail(request.getDoctorEmail());
        newDoctor.setDoctorPhone(request.getDoctorPhone());

        // 3. Assign the found department
        newDoctor.setDepartment(department);

        // 4. Save to the database
        try {
            doctorRepository.save(newDoctor);
            // Return a success response
            return new CreateDoctorResponse(true);
        } catch (Exception e) {
            // Return an error response if the database throws an error
            return new CreateDoctorResponse(false, e.getMessage());
        }
    }
}
