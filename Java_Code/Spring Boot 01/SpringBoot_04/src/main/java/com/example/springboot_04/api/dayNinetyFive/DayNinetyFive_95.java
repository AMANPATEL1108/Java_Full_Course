package com.example.springboot_04.api.dayNinetyFive;

public class DayNinetyFive_95 {
}

//Spring Boot Transactions & ACID Properties
//Overview of Transactions in Spring Boot
//
//In Spring Boot, transactions are used to ensure the ACID properties (Atomicity, Consistency, Isolation, Durability) for operations that interact with the database. Spring simplifies transaction management using @Transactional annotation, enabling declarative transaction handling.
//
//        ACID Properties
//
//Atomicity: A transaction is atomic. Either all operations are completed successfully, or none are.
//
//Consistency: The database remains in a valid state before and after a transaction.
//
//Isolation: Each transaction is isolated from others, preventing interference.
//
//        Durability: Once a transaction is committed, its changes are permanent, even if the system crashes.
//
//Transaction Management in Spring Boot
//
//Spring Boot automatically configures most of the necessary beans for transaction management (e.g., DataSource, EntityManagerFactory, PlatformTransactionManager) when using Spring Data JPA.
//
//You can manage transactions in two ways:
//
//Declarative Transaction Management: Using @Transactional annotation.
//
//Programmatic Transaction Management: Using PlatformTransactionManager directly.
//
//1. Declarative Transaction Management with @Transactional
//
//The @Transactional annotation is the most common and easiest way to handle transactions in Spring Boot. It ensures that a method or class is part of a transaction and handles rollbacks automatically if an exception occurs.
//
//        Example:
//        import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class MyService {
//
//    private final MyRepository myRepository;
//
//    public MyService(MyRepository myRepository) {
//        this.myRepository = myRepository;
//    }
//
//    @Transactional
//    public void performTransaction() {
//        MyEntity entity1 = new MyEntity();
//        entity1.setName("First Entity");
//        myRepository.save(entity1);
//
//        MyEntity entity2 = new MyEntity();
//        entity2.setName("Second Entity");
//        myRepository.save(entity2);
//
//        // Simulate a failure to trigger rollback
//        if (someCondition) {
//            throw new RuntimeException("Simulated exception, rolling back.");
//        }
//    }
//}
//
//
//Explanation:
//
//@Transactional ensures that the method performTransaction() runs within a transaction.
//
//If an exception is thrown, the transaction will be rolled back automatically.
//
//All database changes made in the method will either be committed or rolled back together.
//
//Rollback Behavior:
//
//By default, Spring only rolls back on unchecked exceptions (RuntimeException and its subclasses). You can configure it to roll back on checked exceptions as well.
//
//@Transactional(rollbackFor = Exception.class)
//public void someMethod() throws Exception {
//    // Code that may throw a checked exception
//}
//
//2. Programmatic Transaction Management
//
//While @Transactional is the most common approach, you can also manage transactions programmatically using PlatformTransactionManager.
//
//        Example:
//        import org.springframework.stereotype.Service;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//
//@Service
//public class MyService {
//
//    private final PlatformTransactionManager transactionManager;
//
//    public MyService(PlatformTransactionManager transactionManager) {
//        this.transactionManager = transactionManager;
//    }
//
//    public void performTransactionProgrammatically() {
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//
//        TransactionStatus status = transactionManager.getTransaction(def);
//
//        try {
//            // Your database operations here
//            transactionManager.commit(status);  // Commit the transaction
//        } catch (Exception e) {
//            transactionManager.rollback(status);  // Rollback on failure
//            throw e;
//        }
//    }
//}
//
//
//Explanation:
//
//PlatformTransactionManager is used to begin, commit, and roll back a transaction manually.
//
//This approach is useful when you need more control over the transaction lifecycle.
//
//Transaction Propagation and Isolation Levels
//
//You can customize how Spring handles transactions with propagation and isolation options.
//
//Propagation Types: Determines how transactions interact with each other.
//
//        REQUIRED: Use an existing transaction or create a new one if none exists.
//
//REQUIRES_NEW: Always create a new transaction, suspending the current one.
//
//MANDATORY: Must run within an existing transaction.
//
//NESTED: Creates a nested transaction (useful for sub-transactions).
//
//Isolation Levels: Defines how transactions are isolated from each other.
//
//READ_UNCOMMITTED: Allows dirty reads (not recommended).
//
//READ_COMMITTED: Prevents dirty reads.
//
//        REPEATABLE_READ: Prevents dirty and non-repeatable reads.
//
//SERIALIZABLE: Ensures full isolation, but with the highest overhead.
//
//Example with Propagation and Isolation:
//@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
//public void performTransactionWithStrictIsolation() {
//    // Perform operations here
//}
//
//Complete Example: Spring Boot with Transactions
//Entity:
//@Entity
//public class MyEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//
//    // Getters and setters
//}
//
//Repository:
//        import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface MyRepository extends JpaRepository<MyEntity, Long> {
//}
//
//Service with @Transactional:
//        import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class MyService {
//
//    private final MyRepository myRepository;
//
//    public MyService(MyRepository myRepository) {
//        this.myRepository = myRepository;
//    }
//
//    @Transactional
//    public void saveEntities() {
//        MyEntity entity1 = new MyEntity();
//        entity1.setName("Entity 1");
//        myRepository.save(entity1);
//
//        MyEntity entity2 = new MyEntity();
//        entity2.setName("Entity 2");
//        myRepository.save(entity2);
//    }

