package com.sweetshop.backend.controller;

import com.sweetshop.backend.dto.AuthResponse;
import com.sweetshop.backend.dto.LoginRequest;
import com.sweetshop.backend.dto.RegisterRequest;
import com.sweetshop.backend.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return null; // TDD: Fail first
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return null; // TDD: Fail first
    }
}