package com.swe.project.models;

import com.swe.project.entities.doctors.Doctors;
import com.swe.project.entities.users.Users; // --- MODIFICATION: IMPORT ADDED (for clarity) ---
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate; // --- MODIFICATION: IMPORT ADDED ---

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDetailResponse {

    // --- Fields from Doctors Table ---
    private Integer doctorId;
    private String specialization;
    private Integer experienceYears;
    private String qualifications;
    private String departmentName;

    // --- Fields from Users Table ---
    private Integer userId;
    private String fullName;
    private String email;
    private String phone;
    private String gender;      // --- MODIFICATION: FIELD ADDED ---
    private LocalDate dateOfBirth; // --- MODIFICATION: FIELD ADDED ---
    private String status;      // --- MODIFICATION: FIELD ADDED ---


    /**
     * MODIFICATION: The constructor is now updated to map all necessary fields
     * from both the Doctors and the associated Users entity.
     */
    public DoctorDetailResponse(Doctors doctor) {
        // Get the associated User object for easier access
        Users user = doctor.getUser();

        // Map fields from the Doctors entity
        this.doctorId = doctor.getDoctorId();
        this.specialization = doctor.getSpecialization();
        this.experienceYears = doctor.getExperienceYears();
        this.qualifications = doctor.getQualifications();
        this.departmentName = doctor.getDepartment().getDepartmentName();

        // Map fields from the Users entity
        this.userId = user.getUserId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.dateOfBirth = user.getDateOfBirth();
        this.status = user.getStatus();
    }
}