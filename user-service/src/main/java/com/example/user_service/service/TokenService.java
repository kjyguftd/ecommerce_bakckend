package com.example.user_service.service;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String generateToken(String userCredentials) {
        // Logic to generate token
        StpUtil.login(userCredentials);
        return StpUtil.getTokenValue();
    }

    public boolean validateToken(String token) {
        // Logic to validate token
        return StpUtil.isLogin();
    }

    public String refreshToken(String token) {
        // Logic to refresh token
        StpUtil.renewTimeout(1800); // Renew token expiration time
        return StpUtil.getTokenValue();
    }
}