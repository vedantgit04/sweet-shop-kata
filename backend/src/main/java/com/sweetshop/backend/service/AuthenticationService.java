package com.sweetshop.backend.service;

import com.sweetshop.backend.dto.AuthResponse;
import com.sweetshop.backend.dto.LoginRequest;
import com.sweetshop.backend.dto.RegisterRequest;
import com.sweetshop.backend.model.User;
import com.sweetshop.backend.repository.UserRepository;
import com.sweetshop.backend.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        // Ensure role starts with "ROLE_"
        String role = (request.role() == null || request.role().isEmpty()) ? "ROLE_USER" : request.role();
        if (!role.startsWith("ROLE_")) role = "ROLE_" + role;
        user.setRole(role);

        userRepository.save(user);

        // Pass role here
        String token = jwtService.generateToken(user.getUsername(), user.getRole());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        // Fetch user to get the actual role
        User user = userRepository.findByUsername(request.username()).orElseThrow();

        // Pass role here
        String token = jwtService.generateToken(user.getUsername(), user.getRole());
        return new AuthResponse(token);
    }
}