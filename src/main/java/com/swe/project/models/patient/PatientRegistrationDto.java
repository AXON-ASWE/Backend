package com.swe.project.models.patient;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO đăng ký bệnh nhân")
public class PatientRegistrationDto {
    @NotBlank(message = "Email không được để trống.")
    @Email(message = "Email không hợp lệ. Vui lòng nhập đúng định dạng email.")
    @Schema(example = "patient01@gmail.com", description = "Địa chỉ email hợp lệ của bệnh nhân")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống.")
    @Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
            message = "Mật khẩu phải chứa ít nhất một chữ hoa và một chữ số."
    )
    @Schema(example = "Abc12345", description = "Mật khẩu tối thiểu 8 ký tự, chứa ít nhất 1 chữ hoa và 1 chữ số")
    private String password;

    @NotBlank(message = "Họ và tên không được để trống.")
    @Schema(example = "Nguyễn Văn A", description = "Họ và tên đầy đủ của bệnh nhân")
    private String fullName;
}
