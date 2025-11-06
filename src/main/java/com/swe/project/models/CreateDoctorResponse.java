package com.swe.project.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateDoctorResponse {
    private Boolean success;
    private String message;

    public CreateDoctorResponse(Boolean success) {
        this.success = success;
        this.message = success ? "Doctor created successfully" : "Doctor creation failed";
    }

    public CreateDoctorResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}