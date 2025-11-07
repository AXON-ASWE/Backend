package com.swe.project.controllers;

import com.swe.project.models.SymptomResponse;
import com.swe.project.services.SymptomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/symptoms")
@RequiredArgsConstructor
public class PublicSymptomController {
    private final SymptomService symptomService;

    @Operation(
            summary = "Get all symptom names",
            description = "Retrieves a simplified list of all symptoms, containing only their ID and name. Requires authentication."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of symptoms")
    @GetMapping("/names")
    public ResponseEntity<List<SymptomResponse>> getAllSymptoms() {
        return ResponseEntity.ok(symptomService.getAllSymptoms());
    }
}
