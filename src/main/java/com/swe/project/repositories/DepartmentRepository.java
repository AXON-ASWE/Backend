package com.swe.project.repositories;

import com.swe.project.entities.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Departments, Integer> {

}
