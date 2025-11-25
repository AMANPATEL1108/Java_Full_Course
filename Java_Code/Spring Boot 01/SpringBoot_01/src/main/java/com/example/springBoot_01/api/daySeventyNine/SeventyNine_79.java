package com.example.springBoot_01.api.daySeventyNine;

public class SeventyNine_79 {
}
//
//✅ 1. What is Dependency Injection (DI)? — Beginner Level
//
//Dependency Injection means passing the required objects (dependencies) to a class instead of the class creating them itself.
//
//        ❌ Without DI (tight coupling)
//class Car {
//    private Engine engine = new Engine();  // Car creates its own dependency
//}
//
//✅ With DI (loose coupling)
//class Car {
//    private Engine engine;
//
//    Car(Engine engine) {   // Engine is injected
//        this.engine = engine;
//    }
//}
//
//
//✔ Improves maintainability
//✔ Easier to test (mocking)
//✔ Reduces tight coupling
//
//⚙️ Spring Framework and DI
//
//Spring provides DI automatically using annotations like:
//
//@Component
//
//@Autowired
//
//@Bean
//
//@Service
//
//@Repository
//
//@Configuration
//
//🚀 2. @Component — Beginner / Intermediate Level
//
//@Component tells Spring:
//        ➡ Create an object of this class and manage it.
//
//Example:
//
//        import org.springframework.stereotype.Component;
//
//@Component
//public class Engine {
//    public void start() {
//        System.out.println("Engine started");
//    }
//}
//
//
//Spring creates a bean:
//
//Engine engine = new Engine();
//
//🚀 3. @Autowired — Intermediate Level
//
//@Autowired tells Spring to inject a dependency automatically.
//
//@Component
//public class Car {
//
//    @Autowired
//    private Engine engine;   // Engine injected automatically
//
//    public void drive() {
//        engine.start();
//        System.out.println("Car is moving");
//    }
//}
//
//📌 3 Types of Autowiring
//1️⃣ Field Injection (not recommended but easy)
//@Autowired
//private Engine engine;
//
//2️⃣ Constructor Injection (BEST Practice ✔)
//private final Engine engine;
//
//@Autowired
//public Car(Engine engine) {
//    this.engine = engine;
//}
//
//3️⃣ Setter Injection
//private Engine engine;
//
//@Autowired
//public void setEngine(Engine engine) {
//    this.engine = engine;
//}
//
//⭐ 4. @Service, @Repository, @Controller (Stereotype Annotations)
//
//These are specialized versions of @Component.
//
//        Annotation	Purpose
//@Service	Business logic classes
//@Repository	Data layer / DB operations
//@Controller / @RestController	Web/API controllers
//
//Example:
//
//@Service
//public class PaymentService {
//    public void process() { }
//}
//
//
//Spring treats all of them as beans.
//
//🔧 5. @Bean — Advanced Level
//
//@Bean is used inside a @Configuration class to manually create a bean.
//
//@Configuration
//public class AppConfig {
//
//    @Bean
//    public Engine engine() {
//        return new Engine();
//    }
//}
//
//
//✔ When do we use @Bean?
//
//When you want to return a 3rd-party library object
//
//When object creation needs custom logic
//
//When you can't use @Component
//
//Example:
//
//@Bean
//public ObjectMapper objectMapper() {
//    return new ObjectMapper();
//}
//
//🧠 When to use @Component vs @Bean ?
//Use	@Component	@Bean
//For your own classes	✔ Yes	Possible
//For 3rd-party classes	❌ No	✔ Yes
//Custom logic for object creation	❌ No	✔ Yes
//🧩 6. Advanced: Qualifier, Primary, Lazy, Scope
//✔ @Qualifier — When more than one bean of same type exists
//@Component("petrolEngine")
//class PetrolEngine implements Engine {}
//
//@Component("dieselEngine")
//class DieselEngine implements Engine {}
//
//@Component
//class Car {
//    @Autowired
//    @Qualifier("dieselEngine")
//    private Engine engine;
//}
//
//✔ @Primary — Default bean
//@Primary
//@Component
//class ElectricEngine implements Engine {}
//
//✔ @Lazy — Create bean only when needed
//@Lazy
//@Component
//class HeavyObject {}
//
//✔ @Scope — Change bean lifecycle
//
//Default: singleton
//
//@Scope("prototype")
//@Component
//class PrototypeBean {}
//
//📚 Full Example: Constructor DI with @Component + @Autowired
//@Component
//class Engine {
//    public String getType() { return "V8 Engine"; }
//}
//
//@Component
//class Car {
//
//    private final Engine engine;
//
//    @Autowired
//    public Car(Engine engine) {
//        this.engine = engine;
//    }
//
//    public void printDetails() {
//        System.out.println(engine.getType());
//    }
//}
//
//🏁 Summary Table (Super Simple)
//Annotation	Meaning
//@Component	Spring auto-creates object
//@Service / @Repository	Same as @Component but semantic
//@Autowired	Inject a bean
//@Bean	Manually create bean inside @Configuration
//@Configuration	Class containing @Bean methods
//@Qualifier	Select one bean when multiple exist
//@Primary	Default bean
//@Scope	Singleton/Prototype/Lazy etc.
