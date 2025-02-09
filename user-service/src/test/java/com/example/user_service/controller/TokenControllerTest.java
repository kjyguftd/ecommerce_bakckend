package com.example.user_service.controller;

import com.example.user_service.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class TokenControllerTest {

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private TokenController tokenController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateToken() {
        String userCredentials = "user:password";
        String expectedToken = "generatedToken";

        when(tokenService.generateToken(anyString())).thenReturn(expectedToken);

        ResponseEntity<String> response = tokenController.generateToken(userCredentials);

        assertEquals(ResponseEntity.ok(expectedToken), response);
    }

    @Test
    void validateToken() {
        String token = "validToken";
        when(tokenService.validateToken(anyString())).thenReturn(true);

        ResponseEntity<String> response = tokenController.validateToken(token);

        assertEquals(ResponseEntity.ok("Token is valid"), response);
    }

    @Test
    void refreshToken() {
        String token = "expiredToken";
        String newToken = "newToken";

        when(tokenService.refreshToken(anyString())).thenReturn(newToken);

        ResponseEntity<String> response = tokenController.refreshToken(token);

        assertEquals(ResponseEntity.ok(newToken), response);
    }
}