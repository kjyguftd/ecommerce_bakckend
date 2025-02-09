package com.example.user_service.client;

import com.example.user_service.config.FeignConfig;
import com.example.user_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "application", url = "http://192.168.1.68:8081", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/users/{name}")
    User getUserByName(@PathVariable("name") String name);
}