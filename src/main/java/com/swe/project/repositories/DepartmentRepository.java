package com.swe.project.repositories;

import com.swe.project.entities.departments.Departments;
import com.swe.project.models.DepartmentSuggestionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Departments, Integer> {
    @Query(value = """
    SELECT d.id AS departmentId,
           d.department_name AS departmentName
    FROM t_department_symptom ds
    JOIN t_department d ON ds.department_id = d.id
    WHERE ds.symptom_id IN (:symptomIds)
    GROUP BY d.id, d.department_name
    ORDER BY COUNT(ds.symptom_id) DESC
""", nativeQuery = true)
    List<DepartmentSuggestionDTO> findDepartmentsByMostMatchingSymptoms(@Param("symptomIds") List<Integer> symptomIds);
}
