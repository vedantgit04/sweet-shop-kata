package com.sweetshop.backend.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.sweetshop.backend.BackendApplication.class)
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    void shouldGenerateAndValidateToken() {
        String username = "testuser";
        String role = "ROLE_USER"; // 1. Define a role for the test

        // 2. Generate Token (Pass BOTH username and role)
        String token = jwtService.generateToken(username, role);
        assertNotNull(token, "Token should not be null");

        // 3. Extract Username
        String extractedUsername = jwtService.extractUsername(token);
        assertEquals(username, extractedUsername);

        // 4. Validate Token
        assertTrue(jwtService.validateToken(token, username));
    }
}