package com.example.springboot_08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringBoot08Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot08Application.class, args);
	}

}
