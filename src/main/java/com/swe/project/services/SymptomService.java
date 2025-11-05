package com.swe.project.services;

import com.swe.project.entities.symptoms.Symptom;
import com.swe.project.models.SymptomResponse;
import com.swe.project.repositories.SymptomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}