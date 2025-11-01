package com.swe.project.repositories;

import com.swe.project.entities.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctors, Integer> {
    List<Doctors> findByDepartment_Id(Integer Id);

}
