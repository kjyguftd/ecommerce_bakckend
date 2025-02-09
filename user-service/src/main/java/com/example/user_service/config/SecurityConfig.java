package com.example.user_service.config;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth->auth
                .requestMatchers("/auth/**", "/users/register", "/users/login").permitAll()
                .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        // 配置SaToken的自定义认证
        authenticationManagerBuilder.userDetailsService(username -> {
            // 调用SaToken进行用户认证
            if (StpUtil.isLogin()) {
                // 返回自定义的User
                return new org.springframework.security.core.userdetails.User(username, "1", new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("用户未登录");
            }
        }).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}