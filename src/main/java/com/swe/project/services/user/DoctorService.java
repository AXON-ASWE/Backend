package com.swe.project.services.user;

import com.swe.project.entities.Doctors;
import com.swe.project.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctors> getDoctorsByDepartmentId(Integer departmentId) {
        return doctorRepository.findByDepartment_Id(departmentId);
    }
}
