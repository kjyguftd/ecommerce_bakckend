//package com.example.application.service;
//
//import com.example.user_service.client.UserClient;
//import com.example.user_service.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MonoService {
//
//    private final UserClient userClient;
//
//    @Autowired
//    public MonoService(UserClient userClient) {
//        this.userClient = userClient;
//    }
//
//    public User getUserByName(String name) {
//        return userClient.getUserByName(name);
//    }
//
//    public User getUserById(Long id) {
//        return userClient.getUserById(id);
//    }
//}