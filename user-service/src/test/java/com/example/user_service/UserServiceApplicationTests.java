package com.example.user_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.main.allow-bean-definition-overriding=true",
		"eureka.client.register-with-eureka=false",
		"eureka.client.fetch-registry=false"
})
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
