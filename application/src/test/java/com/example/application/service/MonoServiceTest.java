//package com.example.application.service;
//
//import com.example.user_service.client.UserClient;
//import com.example.user_service.model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//class MonoServiceTest {
//
//    @Mock
//    private UserClient userClient;
//
//    @InjectMocks
//    private MonoService monoService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getUserByName() {
//        String name = "testUser";
//        User mockUser = new User(name, "password", "email@example.com");
//
//        User user = monoService.getUserByName(name);
//        assertEquals(mockUser, user);
//    }
//
//    @Test
//    void getUserById() {
//        Long id = 1L;
//        User mockUser = new User("testUser", "password", "email@example.com");
//
//        when(userClient.getUserById(id)).thenReturn(mockUser);
//
//        User user = monoService.getUserById(id);
//        assertEquals(mockUser, user);
//    }
//}