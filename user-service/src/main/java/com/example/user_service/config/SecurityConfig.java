package com.example.user_service.config;

import cn.dev33.satoken.stp.StpUtil;
import com.example.user_service.filter.SaTokenFilter;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // 禁用CSRF保护
                .authorizeHttpRequests(auth->auth
                .requestMatchers("/auth/**", "/users/register", "/users/login").permitAll()
                .anyRequest().authenticated())
                .addFilterBefore(new SaTokenFilter(), UsernamePasswordAuthenticationFilter.class); // 添加 Sa-Token 过滤器;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//         //配置SaToken的自定义认证
//        authenticationManagerBuilder.userDetailsService(username -> {
//            // 调用SaToken进行用户认证
//            if (StpUtil.isLogin()) {
//                // 返回自定义的User
//                return new org.springframework.security.core.userdetails.User(username, "1", new ArrayList<>());
//            } else {
//                throw new UsernameNotFoundException("用户未登录");
//            }
//        }).passwordEncoder(passwordEncoder());
//
//        return authenticationManagerBuilder.build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            UserService userService, // 注入 UserService
            PasswordEncoder passwordEncoder // 注入 PasswordEncoder
    ) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(username -> {
                    // 调用 UserService 的方法查询用户信息
                    User user = userService.findByUsername(username);
                    return new org.springframework.security.core.userdetails.User(
                            user.getUsername(), // 用户名
                            user.getPassword(), // 密码（需要是加密后的密码）
                            new ArrayList<>() // 权限列表，暂时为空
                    );
                })
                .passwordEncoder(passwordEncoder); // 设置密码编码器

        return authenticationManagerBuilder.build();
    }
}