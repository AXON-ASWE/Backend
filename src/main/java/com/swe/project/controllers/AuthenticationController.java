package com.swe.project.controllers;

import com.swe.project.models.AuthRequest;
import com.swe.project.models.CreateUserResponse;
import com.swe.project.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("auth")
    public ResponseEntity<AuthRequest> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(request);
    }

    @PostMapping("register")
    public ResponseEntity<CreateUserResponse> register(@RequestBody AuthRequest request) {
        CreateUserResponse response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }
}
