package com.swe.project.services;

import com.swe.project.entities.Users;
import com.swe.project.models.AuthRequest;
import com.swe.project.models.CreateUserResponse;
import com.swe.project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public CreateUserResponse createUser(AuthRequest request) {
        // TODO: Hash the Password (later)
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        return new CreateUserResponse(true);
    }
}
