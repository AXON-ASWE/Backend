package com.swe.project.models;


import lombok.Data;

@Data
public class createDoctorsResponse {

    private Boolean success;
    private String message;

    public createDoctorsResponse(Boolean success) {
        this.success = success;
        if (success) {
            this.message = "Doctor created successfully";
        } else {
            this.message = "Doctor creation failed";
        }
    }


    public createDoctorsResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}