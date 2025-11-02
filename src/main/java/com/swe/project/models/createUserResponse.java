package com.swe.project.models;

import lombok.Data;

@Data
public class createUserResponse {
    private Boolean success;
    private String message;

    public createUserResponse(Boolean success) {
        this.success = success;
        if (success){
            this.message = "User created successfully";
        }
        else {
            this.message = "User creation failed";
        }
    }
}
