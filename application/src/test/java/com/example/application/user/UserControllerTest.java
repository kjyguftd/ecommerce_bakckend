package com.example.application.user;

import com.example.user_service.controller.UserController;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserService userService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void getUserByName() {
        String name = "testUser";
        User user = new User(name, "password", "email@example.com");

        User result = userController.getUserByName(name);

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    void registerUser() {
        User user = new User("newUser", "password", "newuser@example.com");

        ResponseEntity<String> response = userController.registerUser(user);

        assertEquals("User registered successfully", response.getBody());
    }

    @Test
    void loginUser() {
        User user = new User("existingUser", "password", "existinguser@example.com");

        ResponseEntity<String> response = userController.loginUser(user);

        assertEquals("User logged in successfully", response.getBody());
    }

    @Test
    void getUser() {
        String username = "existingUser";
        User user = new User(username, "password", "existinguser@example.com");
        when(userService.findByUsername(username)).thenReturn(user);

        ResponseEntity<User> response = userController.getUser(username);

        assertEquals(user, response.getBody());
    }

    @Test
    void getUser_notFound() {
        String username = "nonExistentUser";
        when(userService.findByUsername(username)).thenReturn(null);

        ResponseEntity<User> response = userController.getUser(username);

        assertNull(response.getBody());
    }
}