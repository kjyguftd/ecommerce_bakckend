package com.example.user_service.service;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AutherService {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AutherService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String loginUser(String username, String password) {
        try {
            // 使用 Spring Security 进行认证
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            if (authentication.isAuthenticated()) {
                // 使用 Sa-Token 进行登录
                StpUtil.login(username);
                return StpUtil.getTokenValue();
            } else {
                throw new RuntimeException("Authentication failed");
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid username or password");
        }
    }
}