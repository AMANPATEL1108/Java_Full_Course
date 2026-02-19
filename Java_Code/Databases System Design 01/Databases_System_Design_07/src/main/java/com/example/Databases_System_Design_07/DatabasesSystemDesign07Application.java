package com.example.Databases_System_Design_07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DatabasesSystemDesign07Application {

	public static void main(String[] args) {
		SpringApplication.run(DatabasesSystemDesign07Application.class, args);
	}

}
