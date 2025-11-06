package com.swe.project.models.patient;

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
public class PatientRegistrationDto {
    @NotBlank(message = "Email không được để trống.")
    @Email(message = "Email không hợp lệ. Vui lòng nhập đúng định dạng email.")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống.")
    @Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
            message = "Mật khẩu phải chứa ít nhất một chữ hoa và một chữ số."
    )
    private String password;

    @NotBlank(message = "Họ và tên không được để trống.")
    private String fullName;
}
