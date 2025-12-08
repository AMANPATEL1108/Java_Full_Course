package com.example.springboot_04.api.dayNinetyTwo;

public class NinetyTwo_92 {
}
//
//✅ 1. @OneToOne Relationship
//Example: User ↔ Profile
//
//A user has exactly one profile.
//
//User.java
//@Entity
//public class User {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "profile_id")
//    private Profile profile;
//
//    // getters & setters
//}
//
//Profile.java
//@Entity
//public class Profile {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @OneToOne(mappedBy = "profile")
//    private User user;
//
//    // getters & setters
//}
//
//✅ 2. @OneToMany Relationship
//Example: One Department → Many Employees
//Department.java
//@Entity
//public class Department {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    private String name;
//
//    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
//    private List<Employee> employees = new ArrayList<>();
//
//    // getters & setters
//}
//
//Employee.java
//@Entity
//public class Employee {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    private String name;
//
//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;
//
//    // getters & setters
//}
//
//✅ 3. @ManyToMany Relationship
//Example: Students ↔ Courses
//
//A student can attend many courses, and a course can have many students.
//
//Student.java
//@Entity
//public class Student {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    private String name;
//
//    @ManyToMany
//    @JoinTable(name = "student_course",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "course_id"))
//    private List<Course> courses = new ArrayList<>();
//
//    // getters & setters
//}
//
//Course.java
//@Entity
//public class Course {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    private String title;
//
//    @ManyToMany(mappedBy = "courses")
//    private List<Student> students = new ArrayList<>();
//
//    // getters & setters
//}
//
//⭐ Common Notes
//Cascade Types
//
//ALL – apply all cascades
//
//PERSIST – save children automatically
//
//        MERGE
//
//REMOVE
//
//        REFRESH
//
//DETACH
//
//Fetch Types
//
//LAZY (recommended for lists)
//
//EAGER (loads immediately)
//
//Defaults:
//
//@OneToMany → LAZY
//
//@ManyToOne → EAGER
//
//@ManyToMany → LAZY
//
//@OneToOne → EAGER
