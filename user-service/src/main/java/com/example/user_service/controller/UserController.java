package com.example.user_service.controller;

import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{name}")
    public User getUserByName(@PathVariable("name") String name) {
        // Logic to get user by name
        return new User(name, "password", "email@example.com");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Logic to register user
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        // Logic to authenticate user
        return ResponseEntity.ok("User logged in successfully");
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        // Logic to get user details
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }
}