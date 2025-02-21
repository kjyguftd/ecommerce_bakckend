package com.example.user_service.controller;

import com.example.user_service.model.User;
import com.example.user_service.service.AutherService;
import com.example.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cn.dev33.satoken.stp.StpUtil;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AutherService autherService;

    public UserController(UserService userService, AutherService autherService) {
        this.userService = userService;
        this.autherService = autherService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            String response = userService.registerUser(user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        try {
            String token = autherService.loginUser(user.getUsername(), user.getPassword());
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

//    @GetMapping("/{username}")
//    public ResponseEntity<User> getUser(@PathVariable String username) {
//        User user = userService.findByUsername(username);
//        return ResponseEntity.ok(user);
//    }
// 查询用户信息
    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo() {
        // 检查用户是否登录
        if (!StpUtil.isLogin()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("请先登录");
        }
        // 从 Sa-Token 中获取用户名
        String username = StpUtil.getLoginIdAsString();

        try {
            // 根据用户名查询用户信息
            User user = userService.findByUsername(username);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            // 用户不存在时返回404状态码和错误信息
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}