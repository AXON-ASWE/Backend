package com.swe.project.services.user;

import com.swe.project.entities.Symptom;
import com.swe.project.models.SymptomResponse;
import com.swe.project.repositories.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomService {

    @Autowired
    private SymptomRepository symptomRepository;

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