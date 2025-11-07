package com.swe.project.services.authentication;

import com.swe.project.entities.users.RoleName;
import com.swe.project.entities.users.Users;
import com.swe.project.exception.UserRegistrationException;
import com.swe.project.models.authentication.AdminRegistrationRequest;
import com.swe.project.models.authentication.AuthenticationResponse;
import com.swe.project.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Service đăng ký tài khoản Admin đầu tiên.
 * Tránh phụ thuộc Lombok builder để giảm lỗi annotation lúc soạn thảo.
 */
@Service
public class AdminRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService; // tái sử dụng generateResponse

    public AdminRegistrationService(UserRepository userRepository,
                                    PasswordEncoder passwordEncoder,
                                    AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
    }

    public AuthenticationResponse register(AdminRegistrationRequest request) {
        // Kiểm tra email tồn tại
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserRegistrationException(request.getEmail());
        }

        // Tạo entity Users qua constructor (tránh Lombok builder & setters)
        Users admin = new Users(
                null, // userId tự sinh
                request.getEmail(),
                //passwordEncoder.encode(request.getPassword()),
                request.getPassword(),
                request.getFullName(),
                null, // phone
                null, // gender
                null, // dateOfBirth
                RoleName.ADMIN,
                "ACTIVE",
                LocalDate.now()
        );

        Users saved = userRepository.save(admin);

        // Tái sử dụng logic tạo token từ AuthenticationService
        return authenticationService.generateResponse(saved);
    }
}
