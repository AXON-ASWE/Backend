package com.swe.project.controllers;

import com.swe.project.entities.Symptom;
import com.swe.project.models.SymtomResponse;
import com.swe.project.services.user.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/symptoms")
public class SymptomController {

    @Autowired
    private SymptomService symptomService;

    @GetMapping("/names")
    public ResponseEntity<List<SymtomResponse>> getAllSymptoms() {
        return ResponseEntity.ok(symptomService.getAllSymptoms());
    }
}