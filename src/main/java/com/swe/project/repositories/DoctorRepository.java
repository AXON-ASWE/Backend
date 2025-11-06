package com.swe.project.repositories;

import com.swe.project.entities.doctors.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctors, Integer> {
    List<Doctors> findByDepartment_Id(Integer departmentId);
    Doctors findByUser_UserId(Integer userId);
    Doctors findByDoctorId(Integer doctorId);
}
