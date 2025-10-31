package com.swe.project.controllers;

import com.swe.project.models.AuthRequest;
import com.swe.project.models.createUserResponse;
import com.swe.project.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("auth")
    public ResponseEntity<AuthRequest> authenticate(@RequestBody AuthRequest request){
        return ResponseEntity.ok(request);
    }
    @PostMapping("register")
    public ResponseEntity<createUserResponse> register(@RequestBody AuthRequest request){
        createUserResponse response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }
}
