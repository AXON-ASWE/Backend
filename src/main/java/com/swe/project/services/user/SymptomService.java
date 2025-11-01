package com.swe.project.services.user;

import com.swe.project.entities.Symptom;
import com.swe.project.models.SymtomResponse;
import com.swe.project.repositories.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomService {

    @Autowired
    private SymptomRepository symptomRepository;

    private SymtomResponse convertEntityToResponse(Symptom s) {
        return new SymtomResponse(
                s.getId(),
                s.getSymptomName()
        );
    }

    public List<SymtomResponse> getAllSymptoms() {
        return symptomRepository.findAll().stream()
                .map(this::convertEntityToResponse)
                .toList();
    }
}