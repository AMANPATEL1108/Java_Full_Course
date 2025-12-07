package com.example.springboot_04.api.dayNintyOne;

public class DayNinetyOne_91 {
}

//
//🌱 What is JPA?
//
//JPA (Java Persistence API) is a specification (a set of rules) in Java that helps you map Java objects (classes) to database tables.
//
//JPA = how to access and manage data in a relational database (MySQL, PostgreSQL, etc.) using Java objects.
//
//Spring Boot uses Hibernate as the default JPA implementation.
//
//        🧱 What is an Entity?
//
//An Entity is a simple Java class that represents a table in the database.
//
//Each object of that class = row in the table.
//
//To make a class an Entity, we use annotations like:
//
//@Entity
//@Table(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private String email;
//}
//
//Explanation:
//
//@Entity → tells JPA this class is a database table
//
//@Table → optional, gives table name
//
//@Id → primary key
//
//@GeneratedValue → auto-increment
//
//Fields become columns by default
//
//🧩 Steps in Spring Boot to use JPA
//1️⃣ Add JPA and database dependency in pom.xml
//
//Example (for MySQL):
//
//<dependency>
//    <groupId>org.springframework.boot</groupId>
//    <artifactId>spring-boot-starter-data-jpa</artifactId>
//</dependency>
//
//<dependency>
//    <groupId>mysql</groupId>
//    <artifactId>mysql-connector-j</artifactId>
//</dependency>
//
//        2️⃣ Configure application properties
//spring.datasource.url=jdbc:mysql://localhost:3306/testdb
//spring.datasource.username=root
//spring.datasource.password=1234
//
//spring.jpa.hibernate.ddl-auto=update
//spring.jpa.show-sql=true
//
//        3️⃣ Create an Entity
//
//        (Like shown above)
//
//4️⃣ Create a Repository (DAO layer)
//
//Spring Boot gives easy CRUD (Create-Read-Update-Delete) using JpaRepository:
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//}
//
//
//Now you get:
//
//save()
//
//findAll()
//
//findById()
//
//deleteById()
//
//count()
//
//All for free 🎁
//
//        5️⃣ Use it in a Service or Controller
//@Service
//public class UserService {
//    @Autowired
//    private UserRepository repo;
//
//    public User addUser(User user) {
//        return repo.save(user);
//    }
//
//    public List<User> getUsers() {
//        return repo.findAll();
//    }
//}
//
//🟢 Summary (Very Simple)
//Concept	Meaning
//JPA	A way to handle database using Java objects
//Entity	Java class mapped to database table
//        Repository	Interface that gives CRUD operations
//        Hibernate	JPA implementation used by Spring Boot
//        Annotations	Used to map class → table, field → column
//📌 Want a real full project example (with Controller + API endpoints)?
//
//If you want, I can give you a complete Spring Boot CRUD example with:
//
//        ✔ Entity
//✔ Repository
//✔ Service
//✔ Controller
//✔ API endpoints (GET, POST, PUT, DELETE)
//✔ MySQL setup