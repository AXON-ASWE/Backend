package com.swe.project.repositories;

import com.swe.project.entities.symptoms.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Integer> {
    // This interface inherits the findAll() method we need to get all symptoms.
}