package com.example.user_service.client;

import com.example.user_service.config.FeignConfigTest;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@EnableFeignClients(basePackages = "com.example.user_service.client")
@ContextConfiguration(classes = FeignConfigTest.class)
public class UserClientTest {

    @Autowired
    private UserClient userClient;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetUserByName() {

        User user = new User("exampleName", "password", "email@example.com");
        userRepository.save(user);

        User foundUser = userClient.getUserByName("exampleName");
        assertNotNull(foundUser);
        assertEquals("exampleName", foundUser.getUsername());
    }
}