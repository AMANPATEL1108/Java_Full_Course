package com.example.springboot_04.api.dayNinetyThree;

public class DayNinetyThree_93 {
}

//✅ 1. What Are JPQL and Native Queries?
//
//In Spring Boot (using JPA/Hibernate), you have two main ways to write queries:
//
//Query Type	Description	Syntax Operates On
//JPQL (Java Persistence Query Language)	Object-oriented query language	Entity classes + fields
//Native SQL	Pure SQL written directly	Database tables + columns
//✅ 2. Why Do We Need Both?
//        ✔ Use JPQL when:
//
//You want database-independent queries
//
//You want to query using entities
//
//You want safer, more readable code
//
//✔ Use Native SQL when:
//
//You need complex SQL (joins, subqueries, window functions)
//
//Your query is database-specific (PostgreSQL, Oracle, MySQL features)
//
//You need high-performance manual SQL
//
//✅ 3. Basic Setup (Spring Boot Repository)
//public interface UserRepository extends JpaRepository<User, Long> {
//
//}
//
//⭐ BEGINNER LEVEL
//✅ 4. JPQL Basics
//JPQL works with Entity names, NOT table names.
//
//Example Entity:
//
//@Entity
//public class User {
//    @Id
//    private Long id;
//
//    private String name;
//    private int age;
//}
//
//👉 Simple JPQL Query
//@Query("SELECT u FROM User u WHERE u.age > :age")
//List<User> findUsersOlderThan(@Param("age") int age);
//
//
//Explanation:
//
//User = entity name
//
//u.age = entity field (not database column)
//
//👉 JPQL with LIKE
//@Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
//List<User> searchByName(@Param("name") String name);
//
//👉 JPQL with ORDER BY
//@Query("SELECT u FROM User u ORDER BY u.age DESC")
//List<User> orderByAge();
//
//⭐ INTERMEDIATE LEVEL
//✅ 5. JPQL Joins
//
//If you have a relationship:
//
//@Entity
//class User {
//    @OneToMany(mappedBy = "user")
//    private List<Order> orders;
//}
//
//👉 JPQL Join Example
//@Query("SELECT o FROM Order o JOIN o.user u WHERE u.name = :name")
//List<Order> findOrdersByUserName(String name);
//
//✅ 6. JPQL Update/Delete Queries
//Update:
//@Modifying
//@Transactional
//@Query("UPDATE User u SET u.age = :age WHERE u.id = :id")
//int updateUserAge(Long id, int age);
//
//Delete:
//@Modifying
//@Transactional
//@Query("DELETE FROM User u WHERE u.age < :age")
//int deleteYoungerUsers(int age);
//
//⭐ ADVANCED LEVEL
//🔥 7. Native SQL Basics
//
//Native SQL uses actual table names, column names, and database syntax.
//
//Example (Assume table name = users)
//@Query(value = "SELECT * FROM users WHERE age > :age", nativeQuery = true)
//List<User> getUsersNative(int age);
//
//🔥 8. Native SQL with JOIN
//@Query(value =
//        "SELECT o.* FROM orders o " +
//                "JOIN users u ON o.user_id = u.id " +
//                "WHERE u.name = :name",
//        nativeQuery = true)
//List<Order> getOrdersByUserNameNative(String name);
//
//🔥 9. Native Insert/Update/Delete
//@Modifying
//@Transactional
//@Query(value = "UPDATE users SET age = :age WHERE id = :id", nativeQuery = true)
//int nativeUpdateAge(Long id, int age);
//
//🔥 10. Returning Custom DTO From Native Query
//DTO Class
//public record UserDTO(String name, int age) {}
//
//Query
//@Query(value = "SELECT name, age FROM users WHERE age > :age", nativeQuery = true)
//List<Object[]> getUserDTOs(int age);
//
//Convert manually:
//List<UserDTO> list = repo.getUserDTOs(20).stream()
//        .map(row -> new UserDTO((String) row[0], (Integer) row[1]))
//        .toList();
//
//🔥 11. Advanced: Named Native Queries
//In Entity:
//@NamedNativeQuery(
//        name = "User.findActiveUsers",
//        query = "SELECT * FROM users WHERE active = true",
//        resultClass = User.class
//)
//@Entity
//class User { ... }
//
//In Repository:
//@Query(nativeQuery = true, name = "User.findActiveUsers")
//List<User> getActiveUsers();
//
//🔥 12. Parameters in Native Queries
//Positional
//@Query(value = "SELECT * FROM users WHERE age > ?1", nativeQuery = true)
//List<User> getByAge(int age);
//
//Named
//@Query(value = "SELECT * FROM users WHERE name = :name", nativeQuery = true)
//List<User> getByName(String name);
