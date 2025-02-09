package com.example.user_service.controller;

import com.example.user_service.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateToken(@RequestBody String userCredentials) {
        String token = tokenService.generateToken(userCredentials);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestBody String token) {
        boolean isValid = tokenService.validateToken(token);
        return ResponseEntity.ok(isValid ? "Token is valid" : "Token is invalid");
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestBody String token) {
        String newToken = tokenService.refreshToken(token);
        return ResponseEntity.ok(newToken);
    }
}