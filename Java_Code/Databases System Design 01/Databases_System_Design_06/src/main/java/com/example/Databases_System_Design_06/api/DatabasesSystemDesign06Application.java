package com.example.Databases_System_Design_06.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DatabasesSystemDesign06Application {

	public static void main(String[] args) {
		SpringApplication.run(DatabasesSystemDesign06Application.class, args);
	}

}
