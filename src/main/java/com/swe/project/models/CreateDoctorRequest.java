package com.swe.project.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateDoctorRequest {
    private String doctorName;
    private Integer departmentId;
    private int experience = 0;
    private String doctorEmail;
    private String doctorPhone;
}
