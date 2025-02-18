package com.example.application.user;

import com.example.user_service.client.UserClient;
import com.example.user_service.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserClient userClient;

    public UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/{name}")
    public User getUserByName(@PathVariable("name") String name) {
        return userClient.getUserByName(name);
    }
}
