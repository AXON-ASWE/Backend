package com.swe.project.repositories;

import com.swe.project.entities.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patients, Integer> {
}
