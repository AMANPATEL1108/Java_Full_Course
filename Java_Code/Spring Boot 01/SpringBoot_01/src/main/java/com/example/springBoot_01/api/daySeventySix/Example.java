package com.example.springBoot_01.api.daySeventySix;

public class Example {
}

//Spring Boot is a Java-based framework that makes it easy to create production-ready Spring applications with minimal configuration. It builds on top of the Spring Framework and removes boilerplate by providing:
//
//Auto-configuration: Spring automatically configures components based on dependencies on the classpath.
//
//Embedded servers: Run apps without deploying to an external server (e.g., Tomcat, Jetty, Undertow).
//
//Starter dependencies: Preconfigured dependency bundles (e.g., spring-boot-starter-web).
//
//Production-ready features: Actuator, metrics, health checks.
//
//Opinionated defaults: Sensible defaults so you can start quickly.


//✅ How to Set Up Spring Boot
//
//Below is the simplest setup using Spring Initializr, Maven, and IDE options.
//
// 1. Using Spring Initializr (Easiest Way)
//Steps:
//
//Go to start.spring.io
//
//Select:
//Project: Maven or Gradle
//
//Language: Java
//
//Spring Boot Version: Latest stable
//
//Project Metadata:
//
//Group: com.example
//
//Artifact: demo
//
//Add Dependencies:
//
//Spring Web
//
//        (Optional) Spring Data JPA
//
//(Optional) H2 Database
//
//        Click Generate, download the ZIP, unzip it.
//
//2. Project Structure
//
//        After generating, your project looks like:
//
//demo/
//        ├─ src/main/java/com/example/demo/
//        │   └─ DemoApplication.java
// ├─ src/main/resources/
//        │   ├─ application.properties
// ├─ pom.xml
//
//3. Run Spring Boot Application
//Option A: Using IDE (IntelliJ, Eclipse, VS Code)
//
//Run DemoApplication.java which contains:
//
//@SpringBootApplication
//public class DemoApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
//    }
//}
//
//Option B: Using Terminal
//
//Inside the project folder:
//
//mvn spring-boot:run
//
//4. Create a Simple Controller
//@RestController
//public class HelloController {
//
//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello, Spring Boot!";
//    }
//}
//
//
//Run the app → visit:
//
//  👉 http://localhost:8080/hello
//
//        5. Application Configuration
//
//Edit application.properties:
//
//server.port=9090  //here without this default port is 8080
//spring.application.name=demo-app  //optional
