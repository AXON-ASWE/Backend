package com.swe.project.controllers;

import com.swe.project.models.AddSymptomRequest;
import com.swe.project.models.SymptomResponse;
import com.swe.project.services.SymptomService;
import com.swe.project.models.SymptomDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/symptoms")
@RequiredArgsConstructor
@Tag(name = "Symptom Controller", description = "APIs for managing symptoms")
public class SymptomController {
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

    @Operation(
            summary = "Get a symptom by its ID",
            description = "Retrieves the full details of a single symptom by its unique ID. Requires authentication."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the symptom details"),
            @ApiResponse(responseCode = "404", description = "Symptom not found with the provided ID", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<SymptomDetailResponse> getSymptomById(
            @Parameter(description = "The ID of the symptom to retrieve", required = true, example = "19")
            @PathVariable Integer id) {
        return ResponseEntity.ok(symptomService.getSymptomById(id));
    }

    @Operation(
            summary = "Add a new symptom",
            description = "Creates a new symptom in the database. Requires authentication.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Symptom created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data provided"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid"),
                    @ApiResponse(responseCode = "403", description = "Forbidden - User does not have permission to add symptoms")
            }
    )
    @PostMapping
    public ResponseEntity<SymptomResponse> addSymptom(@RequestBody AddSymptomRequest request) {
        SymptomResponse newSymptom = symptomService.addSymptom(request);
        return new ResponseEntity<>(newSymptom, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update an existing symptom",
            description = "Updates the details of an existing symptom by its ID. Requires authentication."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Symptom updated successfully"),
            @ApiResponse(responseCode = "404", description = "Symptom not found with the provided ID", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<SymptomResponse> updateSymptom(
            @Parameter(description = "The ID of the symptom to update", required = true, example = "19")
            @PathVariable Integer id,
            @RequestBody AddSymptomRequest request) {
        SymptomResponse updatedSymptom = symptomService.updateSymptom(id, request);
        return ResponseEntity.ok(updatedSymptom);
    }

    @Operation(
            summary = "Delete a symptom",
            description = "Deletes a symptom from the database by its ID. Requires authentication."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Symptom deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Symptom not found with the provided ID", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error, possibly due to a foreign key constraint violation if the symptom is still in use.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSymptom(
            @Parameter(description = "The ID of the symptom to delete", required = true, example = "19")
            @PathVariable Integer id) {

        symptomService.deleteSymptom(id);
        return ResponseEntity.noContent().build();
    }
}