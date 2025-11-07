package com.swe.project.services;

import com.swe.project.entities.departments.Departments;
import com.swe.project.entities.symptoms.Symptom;
import com.swe.project.models.AddSymptomRequest;
import com.swe.project.models.SymptomDetailResponse;
import com.swe.project.models.SymptomResponse;
import com.swe.project.exception.SymptomNotFoundException;
import com.swe.project.repositories.SymptomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SymptomService {
    private final SymptomRepository symptomRepository;

    private SymptomResponse convertEntityToResponse(Symptom s) {
        return new SymptomResponse(
                s.getId(),
                s.getSymptomName()
        );
    }

    public List<SymptomResponse> getAllSymptoms() {
        return symptomRepository.findAll().stream()
                .map(this::convertEntityToResponse)
                .toList();
    }

    private SymptomDetailResponse convertEntityToDetailResponse(Symptom s) {
        return new SymptomDetailResponse(
                s.getId(),
                s.getSymptomName(),
                s.getDescription(),
                s.getTag()
        );
    }
    public SymptomDetailResponse getSymptomById(Integer id) {
        Symptom symptom = symptomRepository.findById(id)
                .orElseThrow(() -> new SymptomNotFoundException(id));
        return convertEntityToDetailResponse(symptom);
    }

    public SymptomResponse addSymptom(AddSymptomRequest request) {
        Symptom newSymptom = new Symptom();
        newSymptom.setSymptomName(request.getSymptomName());
        newSymptom.setDescription(request.getDescription());
        newSymptom.setTag(request.getTag());

        Symptom savedSymptom = symptomRepository.save(newSymptom);

        return convertEntityToResponse(savedSymptom);
    }

    public SymptomResponse updateSymptom(Integer id, AddSymptomRequest request) {
        Symptom existingSymptom = symptomRepository.findById(id)
                .orElseThrow(() -> new SymptomNotFoundException(id));

        existingSymptom.setSymptomName(request.getSymptomName());
        existingSymptom.setDescription(request.getDescription());
        existingSymptom.setTag(request.getTag());

        Symptom updatedSymptom = symptomRepository.save(existingSymptom);

        return convertEntityToResponse(updatedSymptom);
    }


    @Transactional
    public void deleteSymptom(Integer id) {
        Symptom symptom = symptomRepository.findById(id)
                .orElseThrow(() -> new SymptomNotFoundException(id));

        List<Departments> departmentsToRemoveFrom = new ArrayList<>(symptom.getDepartments());

        for (Departments department : departmentsToRemoveFrom) {
            department.getSymptoms().remove(symptom);
        }

        symptomRepository.delete(symptom);
    }
}