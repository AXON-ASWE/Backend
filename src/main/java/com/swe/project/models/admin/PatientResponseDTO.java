package com.swe.project.models.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Thông tin bệnh nhân cho Admin")
public class PatientResponseDTO {
    
    @Schema(description = "Patient ID", example = "1")
    private Integer patientId;
    
    @Schema(description = "User ID", example = "5")
    private Integer userId;
    
    @Schema(description = "Email", example = "patient@example.com")
    private String email;
    
    @Schema(description = "Họ tên", example = "Nguyễn Văn A")
    private String fullName;
    
    @Schema(description = "Số điện thoại", example = "0901234567")
    private String phone;
    
    @Schema(description = "Giới tính", example = "Nam")
    private String gender;
    
    @Schema(description = "Ngày sinh", example = "1990-01-15")
    private LocalDate dateOfBirth;
    
    @Schema(description = "Địa chỉ", example = "123 Nguyễn Huệ, Q1, TPHCM")
    private String address;
    
    @Schema(description = "Liên hệ khẩn cấp", example = "0987654321")
    private String emergencyContact;
    
    @Schema(description = "Số bảo hiểm", example = "BH123456789")
    private String insuranceNumber;
    
    @Schema(description = "Trạng thái tài khoản", example = "ACTIVE")
    private String status;
    
    @Schema(description = "Ngày tạo", example = "2024-01-01")
    private LocalDate createdAt;
}
