package com.swe.project.repositories;

import com.swe.project.entities.doctors.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.print.Doc;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctors, Integer> {
    List<Doctors> findByDepartment_Id(Integer departmentId);
    Doctors findByUser_UserId(Integer userId);
    Doctors findByDoctorId(Integer doctorId);
    @Query("""
        SELECT d
        FROM Doctors d
        WHERE d.department.id = :departmentId
          AND d.user.status = 'ACTIVE'
    """)
    List<Doctors> findActiveDoctorsByDepartmentId(@Param("departmentId") Integer departmentId);
}
