package com.swe.project.models;
import lombok.Data;

@Data
public class createDoctorsRequest {
    private String doctorName;
    private Integer departmentId;
    private int experience = 0;
    private String doctorEmail;
    private String doctorPhone;
}
