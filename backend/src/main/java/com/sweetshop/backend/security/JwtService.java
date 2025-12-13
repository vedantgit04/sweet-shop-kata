package com.sweetshop.backend.security;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.function.Function;
import io.jsonwebtoken.Claims;

@Service
public class JwtService {

    public String generateToken(String username) {
        return null; // TDD: Fail first
    }

    public String extractUsername(String token) {
        return null; // TDD: Fail first
    }

    public boolean validateToken(String token, String username) {
        return false; // TDD: Fail first
    }
}