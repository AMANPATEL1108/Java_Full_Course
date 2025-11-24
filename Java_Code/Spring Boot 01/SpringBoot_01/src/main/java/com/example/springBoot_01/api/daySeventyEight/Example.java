package com.example.springBoot_01.api.daySeventyEight;

public class Example {
}
//
//🧩 1. CONTROLLER (Handles HTTP Requests)
//✔️ What it does:
//
//Listens for incoming HTTP requests (GET, POST, PUT, DELETE)
//
//Sends responses back to the client
//
//Should NOT contain business logic
//
//Should call the Service layer to do the real work
//
//✔️ Annotation:
//@RestController
//
//✔️ Example:
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/{id}")
//    public User getUser(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }
//}
//
//⚙️ 2. SERVICE (Business Logic Layer)
//✔️ What it does:
//
//Contains business rules and processing logic
//
//Should not handle HTTP or database code directly
//
//Acts as the middle layer between Controller and Repository
//
//✔️ Annotation:
//@Service
//
//✔️ Example:
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public User getUserById(Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//}
//
//🗄️ 3. REPOSITORY (Data Access Layer)
//✔️ What it does:
//
//Talks to the database
//
//Performs CRUD operations (Create, Read, Update, Delete)
//
//Spring Data JPA provides methods automatically → less code!
//
//        ✔️ Annotation:
//@Repository
//
//
//But usually, you don’t need to write it because Spring Data adds it automatically when you extend JpaRepository.
//
//✔️ Example:
//public interface UserRepository extends JpaRepository<User, Long> {
//}
//
//🧬 How They Work Together (Flow)
//Client → Controller → Service → Repository → Database
//                                  ↑
//returns data
//
//Example interaction:
//
//Client calls /users/5
//
//Controller receives the request and calls userService.getUserById(5)
//
//Service calls userRepository.findById(5)
//
//Repository queries the database
//
//Result flows back: Repository → Service → Controller → Client
