package com.example.user_service.service;

import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 注入 PasswordEncoder
    //private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        //this.authenticationManager = authenticationManager;
    }

    // 用户注册
    // todo 是否接入spring security？
    public String registerUser(User user) {
        if (existsByUsername(user.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }
        if (existsByEmail(user.getEmail())) {
            throw new RuntimeException("邮箱已注册");
        }
        //TODO: 接入邮箱验证
        saveUser(user);
        return "注册成功";
    }


//    public String loginUser(String username, String password) {
//        try {
//            // 使用 Spring Security 进行认证
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            if (authentication.isAuthenticated()) {
//                // 使用 Sa-Token 进行登录
//                StpUtil.login(username);
//                return StpUtil.getTokenValue();
//            } else {
//                throw new RuntimeException("Authentication failed");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Invalid username or password");
//        }
//    }

    // 保存用户
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole(1); // 默认 role = 1
        user.setCreateTime(LocalDateTime.now()); // 设置创建时间
        user.setUpdateTime(LocalDateTime.now()); // 设置更新时间

        userRepository.save(user);
    }

    public User findByUsername(String username) {
        // Logic to find user by username
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}