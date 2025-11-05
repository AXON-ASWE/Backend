package com.swe.project.models;

import com.swe.project.entities.doctors.Doctors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponse {
    private Integer id;
    private String doctorName;
    private Integer departmentId;
    private String departmentName;
    private Integer experience;
    private String doctorEmail;
    private String doctorPhone;

    public DoctorResponse(Doctors doctor) {
        this.id = doctor.getUserId();
        this.doctorName = doctor.getFullName();
        this.departmentId = doctor.getDepartment().getId();
        this.departmentName = doctor.getDepartment().getDepartmentName();
        this.experience = doctor.getExperienceYears();
        this.doctorEmail = doctor.getEmail();
        this.doctorPhone = doctor.getPhone();
    }
}
