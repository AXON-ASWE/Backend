package com.swe.project.services.authentication;

import com.swe.project.entities.users.Users;
import com.swe.project.exception.UserNotFoundException;
import com.swe.project.exception.UserWrongPasswordException;
import com.swe.project.models.authentication.AuthLoginRequest;
import com.swe.project.models.authentication.AuthenticationResponse;
import com.swe.project.repositories.UserRepository;
import com.swe.project.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthenticationResponse login(AuthLoginRequest request) {

        Users user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);

//        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
//            throw new UserWrongPasswordException();
//        }
        if (!request.getPassword().equals(user.getPasswordHash())) {
            throw new UserWrongPasswordException();
        }

        return generateResponse(user);
    }

    public AuthenticationResponse generateResponse(Users user) {

        String accessToken = jwtUtil.generateToken(
                user.getUserId(),
                user.getEmail(),
                user.getRole(),
                false
        );

        String refreshToken = jwtUtil.generateToken(
                user.getUserId(),
                user.getEmail(),
                user.getRole(),
                true
        );

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .role(user.getRole().name())
                .expiration(jwtUtil.extractExpiration(accessToken).getTime())
                .build();
    }
}
