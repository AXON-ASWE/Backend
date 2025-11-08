package com.swe.project.models;

import lombok.Data;

@Data
public class UpdateDoctorRequest {
    // Fields from the Users table that can be updated
    private String fullName;
    private String phone;

    // Fields from the Doctors table that can be updated
    private String specialization;
    private Integer experienceYears;
    private String qualifications;
    private Integer departmentId;
}