package com.swe.project.services.user;

import com.swe.project.entities.Users;
import com.swe.project.models.AuthRequest;
import com.swe.project.models.createUserResponse;
import com.swe.project.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public createUserResponse createUser(AuthRequest request){
        userRepository.save(new Users(request.getUsername(), request.getPassword()));
        return new createUserResponse(true);
    }
}
