package com.example.springBoot_01.api.daySeventySeven;

public class Example {
}
//
//🚀 1. What is @SpringBootApplication?
//
//@SpringBootApplication is a convenience annotation in Spring Boot.
//It combines three important Spring annotations:
//
//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan
//
//✔️ What each does:
//1. @SpringBootConfiguration
//
//Marks the class as a configuration class.
//
//Means Spring will read this class to configure the application.
//
//2. @EnableAutoConfiguration
//
//Tells Spring Boot to automatically configure beans based on the dependencies in the classpath.
//
//Example: If you include Spring Web, it auto-configures a Tomcat server.
//
// 3. @ComponentScan
//
//Automatically scans your package and sub-packages for:
//
//@RestController
//
//@Service
//
//@Repository
//
//@Component
//
//So Spring finds and creates these beans automatically.
//
//✔️ In short:
//
//@SpringBootApplication = Start Spring + auto-configure + scan components.

//=====================
//
//🌐 2. REST Basics (with Spring Boot)
//
//REST = Representational State Transfer
//It is a way for clients and servers to communicate using HTTP methods.
//
//The 4 main HTTP methods used in REST:
//HTTP Method	Purpose
//GET	Read data
//POST	Create new data
//PUT	Update existing data
//DELETE	Delete data
//
//Spring Boot makes REST APIs very simple using @RestController.
//
//📌 3. Example REST Application (Spring Boot)
//Main class:
//@SpringBootApplication
//public class MyApp {
//    public static void main(String[] args) {
//        SpringApplication.run(MyApp.class, args);
//    }
//}
//
//REST Controller Example:
//@RestController
//@RequestMapping("/api")
//public class HelloController {
//
//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello REST!";
//    }
//
//    @PostMapping("/hello")
//    public String createHello(@RequestBody String name) {
//        return "Hello " + name;
//    }
//}
//
//⚙️ 4. What Happens When You Run It
//
//Your app starts an embedded Tomcat server (8xxx port).
//
//Spring scans your packages and registers your controller.
//
//Visiting:
//
//GET http://localhost:8080/api/hello
//
//
//returns:
//
//Hello REST!