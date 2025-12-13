package com.sweetshop.backend.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;

// We use @SpringBootTest to load the actual service context
@SpringBootTest(classes = com.sweetshop.backend.BackendApplication.class)
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    void shouldGenerateAndValidateToken() {
        String username = "testuser";

        // 1. Generate Token
        String token = jwtService.generateToken(username);
        assertNotNull(token, "Token should not be null");

        // 2. Extract Username
        String extractedUsername = jwtService.extractUsername(token);
        assertEquals(username, extractedUsername);

        // 3. Validate Token
        assertTrue(jwtService.validateToken(token, username));
    }
}
