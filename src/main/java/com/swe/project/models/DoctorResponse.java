package com.swe.project.models;

import com.swe.project.entities.doctors.Doctors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponse {
    private Integer userId;
    private Integer doctorId;
    private String doctorName;
    private Integer departmentId;
    private String departmentName;
    private Integer experience;
    private String doctorEmail;
    private String doctorPhone;
    private String doctorStatus;

    public DoctorResponse(Doctors doctor) {
        this.userId = doctor.getUser().getUserId();
        this.doctorId = doctor.getDoctorId();
        this.doctorName = doctor.getUser().getFullName();
        this.departmentId = doctor.getDepartment().getId();
        this.departmentName = doctor.getDepartment().getDepartmentName();
        this.experience = doctor.getExperienceYears();
        this.doctorEmail = doctor.getUser().getEmail();
        this.doctorPhone = doctor.getUser().getPhone();
        this.doctorStatus = doctor.getUser().getStatus();
    }
}
