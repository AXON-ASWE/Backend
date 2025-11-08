package com.swe.project.services;

import com.swe.project.entities.appointments.Appointments;
import com.swe.project.entities.departments.Departments;
import com.swe.project.entities.doctors.Doctors;
import com.swe.project.entities.users.RoleName;
import com.swe.project.entities.users.Users;
import com.swe.project.exception.DoctorNotFoundException;
import com.swe.project.models.DoctorDetailResponse;
import com.swe.project.models.CreateDoctorRequest;
import com.swe.project.models.CreateDoctorResponse;
import com.swe.project.models.DoctorResponse;
import com.swe.project.repositories.AppointmentRepository;
import com.swe.project.repositories.DepartmentRepository;
import com.swe.project.repositories.DoctorRepository;
import com.swe.project.repositories.UserRepository;
import com.swe.project.models.UpdateDoctorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    public List<DoctorResponse> getDoctorsByDepartmentId(Integer departmentId) {
        List<Doctors> doctors = doctorRepository.findActiveDoctorsByDepartmentId(departmentId);
        return doctors.stream()
                .map(DoctorResponse::new)
                .collect(Collectors.toList());
    }

    public List<DoctorResponse> getAllDoctors() {
        List<Doctors> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(DoctorResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Business logic to create a new Doctor profile
     */
    @Transactional
    public CreateDoctorResponse createDoctor(CreateDoctorRequest request, PasswordEncoder passwordEncoder) {
        // 1. Find the department
        Departments department = departmentRepository.findById(request.getDepartmentId())
                .orElse(null);

        if (department == null) {
            // Return an error response if not found
            return new CreateDoctorResponse(false, "Department not found with ID: " + request.getDepartmentId());
        }

        // 2. Create a new Users object
        Users newUser = Users.builder()
                .passwordHash(
                         request.getPassword()
                )
                .fullName(request.getDoctorName())
                .email(request.getDoctorEmail())
                .phone(request.getDoctorPhone())
                .role(RoleName.DOCTOR)
                .status("ACTIVE")
                .createdAt(LocalDate.now())
                .build();

        newUser = userRepository.save(newUser);

        // 3. Create a new Doctors object
        Doctors newDoctor = Doctors.builder()
                .user(newUser)
                .experienceYears(request.getExperience())
                .department(department)
                .specialization("Bac si chuyen khoa")
                .build();

        // 4. Save to the database
        try {
            doctorRepository.save(newDoctor);
            // Return a success response
            return new CreateDoctorResponse(true);
        } catch (Exception e) {
            // Return an error response if the database throws an error
            return new CreateDoctorResponse(false, e.getMessage());
        }
    }


    public DoctorDetailResponse getDoctorById(Integer id) {
        Doctors doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id));
        return new DoctorDetailResponse(doctor);
    }


    @Transactional
    public DoctorDetailResponse updateDoctor(Integer id, UpdateDoctorRequest request) {
        Doctors doctorToUpdate = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id));

        Users userToUpdate = doctorToUpdate.getUser();
        userToUpdate.setFullName(request.getFullName());
        userToUpdate.setPhone(request.getPhone());

        doctorToUpdate.setSpecialization(request.getSpecialization());
        doctorToUpdate.setExperienceYears(request.getExperienceYears());
        doctorToUpdate.setQualifications(request.getQualifications());

        if (request.getDepartmentId() != null) {
            Departments newDepartment = departmentRepository.findById(request.getDepartmentId())
                    .orElse(null); // Or throw a DepartmentNotFoundException
            if (newDepartment != null) {
                doctorToUpdate.setDepartment(newDepartment);
            }
        }
        return new DoctorDetailResponse(doctorToUpdate);
    }

    @Transactional
    public void deactivateDoctor(Integer id) {
        // 1. Find the doctor by their ID or throw an error if not found.
        Doctors doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id));

        // 2. Get the associated user.
        Users user = doctor.getUser();

        // 3. Change the user's status.
        user.setStatus("INACTIVE");
        List<Appointments> appointments = appointmentRepository.findByDoctor_DoctorId(id);
        appointments.forEach(appointment -> {
            if(appointment.getStatus().equals("SCHEDULED")) {
                appointment.setStatus("CANCELLED");
            }
        });
        // The @Transactional annotation ensures this change is saved to the database.
    }
}
