package com.example.springboot_04.api.dayNinetyFour;

public class DayNinetyFour_94 {
}
//⭐ 1. What is Pagination & Sorting?
//        ✔ Pagination
//
//Breaks total records into multiple pages.
//Example: page 0, page 1, page 2…
//
//        ✔ Sorting
//
//Sorts results by a given field.
//Example: sort by age descending.
//
//Spring Data JPA provides built-in classes:
//
//Pageable
//
//        PageRequest
//
//Sort
//
//        Page
//
//⭐ 2. Repository Setup
//public interface UserRepository extends JpaRepository<User, Long> {
//}
//
//
//You automatically get pagination and sorting support via:
//findAll(Pageable pageable)
//
//⭐ BEGINNER LEVEL
//✅ 3. Basic Pagination
//✔ Request page number and page size
//Pageable pageable = PageRequest.of(0, 5); // page 0, size 5
//Page<User> users = userRepository.findAll(pageable);
//
//✔ Check page details
//users.getContent();     // actual data
//users.getTotalPages();  // total pages
//users.getTotalElements(); // total rows
//users.getNumber();      // current page
//users.getSize();        // size
//users.isLast();         // last page?
//
//⭐ 4. Pagination in Controller (REST API)
//✔ API Example
//@GetMapping("/users")
//public Page<User> getUsers(
//        @RequestParam int page,
//        @RequestParam int size) {
//
//    Pageable pageable = PageRequest.of(page, size);
//    return userRepository.findAll(pageable);
//}
//
//Request:
//GET /users?page=0&size=10
//
//        ⭐ INTERMEDIATE LEVEL
//✅ 5. Pagination + Sorting
//✔ Sorting in PageRequest
//Pageable pageable = PageRequest.of(
//        0,
//        10,
//        Sort.by("age").ascending()
//);
//
//Another Example (DESC)
//Sort sort = Sort.by("name").descending();
//Pageable pageable = PageRequest.of(0, 10, sort);
//
//⭐ 6. Multiple Sorting Fields
//Sort sort = Sort.by("age").descending()
//        .and(Sort.by("name").ascending());
//
//Pageable pageable = PageRequest.of(0, 10, sort);
//
//⭐ 7. Pagination + Sorting + Custom Query (JPQL)
//Example:
//@Query("SELECT u FROM User u WHERE u.age > :age")
//Page<User> findUsers(@Param("age") int age, Pageable pageable);
//
//
//Usage:
//
//Page<User> users = userRepository.findUsers(20,
//        PageRequest.of(0, 10, Sort.by("name")));
//
//⭐ ADVANCED LEVEL
//🔥 8. Pagination + Native SQL Query
//
//Native must return Pageable compatible results.
//
//        Step 1: Native Query
//@Query(
//        value = "SELECT * FROM users WHERE age > :age",
//        countQuery = "SELECT count(*) FROM users WHERE age > :age",
//        nativeQuery = true
//)
//Page<User> findNative(@Param("age") int age, Pageable pageable);
//
//Why 2 queries?
//
//value → actual data query
//
//countQuery → count total rows
//Spring Data needs both for pagination.
//
//🔥 9. Dynamic Pagination & Sorting
//        Controller
//@GetMapping("/users")
//public Page<User> list(
//        @RequestParam(defaultValue = "0") int page,
//        @RequestParam(defaultValue = "10") int size,
//        @RequestParam(defaultValue = "id") String sortBy,
//        @RequestParam(defaultValue = "asc") String order) {
//
//    Sort sort = order.equals("asc") ?
//            Sort.by(sortBy).ascending() :
//            Sort.by(sortBy).descending();
//
//    Pageable pageable = PageRequest.of(page, size, sort);
//
//    return userRepository.findAll(pageable);
//}
//
//🔥 10. Pagination + Specification (Advanced Filtering)
//public Page<User> searchUsers(UserSpecification spec, Pageable pageable) {
//    return userRepository.findAll(spec, pageable);
//}
//
//
//This allows complex filtering + pagination + sorting.
//
//🔥 11. Return Pagination in Custom DTO
//public record PageResponse<T>(
//        List<T> content,
//        int page,
//        int size,
//        long totalElements,
//        int totalPages
//) {}
//
//
//Controller:
//
//Page<User> p = userRepository.findAll(pageable);
//
//return new PageResponse<>(
//        p.getContent(),
//    p.getNumber(),
//    p.getSize(),
//    p.getTotalElements(),
//    p.getTotalPages()
//);
//
//        🔥 12. Best Practices
//✔ Always validate page & size
//
//Keep size within limits (e.g., max 50).
//
//        ✔ Use countQuery for Native SQL
//
//This avoids performance issues.
//
//        ✔ Prefer database indexing on sorted fields
//
//Example:
//
//CREATE INDEX idx_user_age ON users(age);
//
//✔ Avoid sorting on unindexed large columns
//
//It slows queries drastically.