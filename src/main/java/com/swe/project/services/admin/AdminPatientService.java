package com.swe.project.services.admin;

import com.swe.project.entities.patients.Patients;
import com.swe.project.entities.users.Users;
import com.swe.project.models.admin.PatientResponseDTO;
import com.swe.project.models.admin.UpdatePatientRequest;
import com.swe.project.repositories.PatientRepository;
import com.swe.project.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminPatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public AdminPatientService(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    /**
     * Lấy danh sách tất cả bệnh nhân
     */
    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Cập nhật thông tin bệnh nhân
     */
    @Transactional
    public PatientResponseDTO updatePatient(Integer patientId, UpdatePatientRequest request) {
        Patients patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy bệnh nhân với ID: " + patientId));

        Users user = patient.getUser();

        // Cập nhật thông tin User (nếu có)
        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            user.setEmail(request.getEmail());
        }
        if (request.getFullName() != null && !request.getFullName().isEmpty()) {
            user.setFullName(request.getFullName());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        if (request.getGender() != null) {
            user.setGender(request.getGender());
        }
        if (request.getDateOfBirth() != null) {
            user.setDateOfBirth(request.getDateOfBirth());
        }
        if (request.getStatus() != null && !request.getStatus().isEmpty()) {
            user.setStatus(request.getStatus());
        }

        // Cập nhật thông tin Patient (nếu có)
        if (request.getAddress() != null) {
            patient.setAddress(request.getAddress());
        }
        if (request.getEmergencyContact() != null) {
            patient.setEmergencyContact(request.getEmergencyContact());
        }
        if (request.getInsuranceNumber() != null) {
            patient.setInsuranceNumber(request.getInsuranceNumber());
        }

        userRepository.save(user);
        Patients updatedPatient = patientRepository.save(patient);

        return convertToDTO(updatedPatient);
    }

    /**
     * Xóa bệnh nhân (soft delete bằng cách set status = INACTIVE)
     */
    @Transactional
    public void deletePatient(Integer patientId) {
        Patients patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy bệnh nhân với ID: " + patientId));

        Users user = patient.getUser();
        user.setStatus("INACTIVE");
        userRepository.save(user);
    }

    /**
     * Convert entity sang DTO
     */
    private PatientResponseDTO convertToDTO(Patients patient) {
        Users user = patient.getUser();
        return PatientResponseDTO.builder()
                .patientId(patient.getPatientId())
                .userId(user.getUserId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth())
                .address(patient.getAddress())
                .emergencyContact(patient.getEmergencyContact())
                .insuranceNumber(patient.getInsuranceNumber())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
