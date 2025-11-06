package com.swe.project.controllers.Authentication;

import com.swe.project.models.authentication.AuthLoginRequest;
import com.swe.project.models.authentication.AuthenticationResponse;
import com.swe.project.services.authentication.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    public final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthLoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

}
