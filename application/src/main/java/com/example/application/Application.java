package com.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// Main application class ro run the application
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.example.user_service.client")
public class Application {

	// Main method to run the application
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
