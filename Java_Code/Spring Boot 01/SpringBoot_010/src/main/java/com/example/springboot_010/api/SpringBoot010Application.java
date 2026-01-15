package com.example.springboot_010.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBoot010Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot010Application.class, args);
	}

}
