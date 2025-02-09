package com.example.user_service.service;

import com.example.user_service.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User findByUsername(String username) {
        // Logic to find user by username
        return new User("1","1","1"); // Replace with actual implementation
    }
}