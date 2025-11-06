package com.swe.project.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserResponse {
    private Boolean success;
    private String message;

    public CreateUserResponse(Boolean success) {
        this.success = success;
        this.message = success ? "User created successfully" : "User creation failed";
    }

    public CreateUserResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
