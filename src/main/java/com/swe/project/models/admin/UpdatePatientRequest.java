package com.swe.project.models.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO cập nhật thông tin bệnh nhân")
public class UpdatePatientRequest {
    
    @Email(message = "Email không hợp lệ")
    @Schema(description = "Email", example = "patient@example.com")
    private String email;
    
    @Schema(description = "Họ tên", example = "Nguyễn Văn A")
    private String fullName;
    
    @Pattern(regexp = "^(0|\\+84)[0-9]{9,10}$", message = "Số điện thoại không hợp lệ")
    @Schema(description = "Số điện thoại", example = "0901234567")
    private String phone;
    
    @Pattern(regexp = "^(Nam|Nữ|Khác)$", message = "Giới tính phải là Nam, Nữ hoặc Khác")
    @Schema(description = "Giới tính", example = "Nam")
    private String gender;
    
    @Schema(description = "Ngày sinh", example = "1990-01-15")
    private LocalDate dateOfBirth;
    
    @Schema(description = "Địa chỉ", example = "123 Nguyễn Huệ, Q1, TPHCM")
    private String address;
    
    @Pattern(regexp = "^(0|\\+84)[0-9]{9,10}$", message = "Số điện thoại khẩn cấp không hợp lệ")
    @Schema(description = "Liên hệ khẩn cấp", example = "0987654321")
    private String emergencyContact;
    
    @Schema(description = "Số bảo hiểm", example = "BH123456789")
    private String insuranceNumber;
    
    @Pattern(regexp = "^(ACTIVE|INACTIVE|BANNED)$", message = "Trạng thái phải là ACTIVE, INACTIVE hoặc BANNED")
    @Schema(description = "Trạng thái tài khoản", example = "ACTIVE")
    private String status;
}
