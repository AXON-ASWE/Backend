package com.swe.project.repositories;

import com.swe.project.entities.patients.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patients, Integer> {
    Optional<Patients> findByEmailAndStatus(String email, String status);
}
