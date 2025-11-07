package com.swe.project.models.authentication;

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
@Schema(description = "DTO đăng ký admin")
public class AdminRegistrationRequest {
    @NotBlank(message = "Email không được để trống.")
    @Email(message = "Email không hợp lệ.")
    @Schema(example = "admin01@gmail.com")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống.")
    @Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "Mật khẩu phải chứa ít nhất một chữ hoa và một chữ số.")
    @Schema(example = "Admin123")
    private String password;

    @NotBlank(message = "Họ và tên không được để trống.")
    @Schema(example = "Trần Quản Trị")
    private String fullName;

    // Explicit getters to avoid Lombok annotation processing issues in editor
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
}
