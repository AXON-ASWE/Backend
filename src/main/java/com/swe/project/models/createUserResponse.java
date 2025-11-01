package com.swe.project.models;

import lombok.Data;

@Data
public class createUserResponse {
    private Boolean success;
    private String message;

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
