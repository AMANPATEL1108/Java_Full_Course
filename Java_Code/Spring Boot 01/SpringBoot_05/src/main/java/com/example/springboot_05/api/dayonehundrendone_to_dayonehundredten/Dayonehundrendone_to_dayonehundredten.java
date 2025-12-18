package com.example.springboot_05.api.dayonehundrendone_to_dayonehundredten;

public class Dayonehundrendone_to_dayonehundredten {
}
/*
Beginner Level: Basic SQL Queries and Joins

At the beginner level, you'll first learn the basics of SQL—understanding how databases are structured, writing simple queries, and learning about the different types of joins.

Simple SQL Queries:

Learn to write basic SELECT queries to fetch data from tables.

Filtering data using WHERE, and ordering with ORDER BY.

Using LIMIT for pagination or limiting results.

Example:

SELECT * FROM users WHERE age > 30 ORDER BY name ASC;


Basic Joins:

INNER JOIN: Combine rows from two or more tables based on a condition.

LEFT JOIN (or LEFT OUTER JOIN): Return all records from the left table and matched rows from the right table.

RIGHT JOIN (or RIGHT OUTER JOIN): Return all records from the right table and matched rows from the left table.

Example of INNER JOIN:

SELECT u.name, o.order_id
FROM users u
INNER JOIN orders o ON u.user_id = o.user_id;


Example of LEFT JOIN:

SELECT u.name, o.order_id
FROM users u
LEFT JOIN orders o ON u.user_id = o.user_id;

In Spring Boot:

You can use Spring Data JPA (or JdbcTemplate for more control) to interact with the database.

Use @Entity to represent the tables, and @ManyToOne, @OneToMany annotations for relationships.

Example:

@Entity
public class User {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}

@Entity
public class Order {
    @Id
    private Long id;
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}


Simple Aggregations:

Understand basic aggregation functions: COUNT, SUM, AVG, MAX, and MIN.

Use GROUP BY to aggregate data.

Example:

SELECT user_id, COUNT(order_id) AS order_count
FROM orders
GROUP BY user_id;

Spring Boot:

Use @Query annotations with JPQL for custom queries.

@Query("SELECT u, COUNT(o) FROM User u LEFT JOIN u.orders o GROUP BY u")
List<Object[]> getUserOrderCount();

Intermediate Level: Complex Joins, Indexing, and Basic Optimization

Advanced Joins:

FULL OUTER JOIN: Returns all records when there is a match in either left (table1) or right (table2) table.

SELF JOIN: Join a table with itself.

Example of FULL OUTER JOIN:

SELECT u.name, o.order_id
FROM users u
FULL OUTER JOIN orders o ON u.user_id = o.user_id;


Indexing:

What is Indexing?: Indexes improve the speed of data retrieval operations on a database.

Learn about creating indexes in SQL:

Example:

CREATE INDEX idx_user_name ON users(name);


When to Use Indexing?: Index frequently searched columns or columns used in JOIN, WHERE, or ORDER BY clauses.

In Spring Boot:

Spring Data JPA doesn't directly create indexes, but you can use @Table annotations for index creation.

@Entity
@Table(indexes = @Index(name = "idx_name", columnList = "name"))
public class User {
    // fields and methods
}


Basic Query Optimization:

Use of EXPLAIN: Use EXPLAIN in SQL to analyze and optimize queries.

Avoid **SELECT *** and instead specify the columns you need.

Use pagination for large result sets.

Example of EXPLAIN:

EXPLAIN SELECT * FROM users WHERE age > 30;

Advanced Level: Query Optimization, Complex Relationships, and Performance Tuning

Advanced Indexing:

Composite Index: Create an index on multiple columns.

Unique Index: Ensure values in the indexed column are unique.

Example of Composite Index:

CREATE INDEX idx_user_email ON users(name, email);


Clustered Index: Ensure physical order of rows in a table matches the index order.

Full-text Search Index: For searching text-heavy fields.

Example:

CREATE FULLTEXT INDEX ft_idx_name ON users(name);


Complex SQL Queries:

Subqueries: Learn how to use subqueries in SELECT, WHERE, or FROM clauses.

Example:

SELECT name, (SELECT COUNT(*) FROM orders WHERE user_id = u.user_id) AS order_count
FROM users u;


Union & Intersection: Combine multiple SELECT queries with UNION or INTERSECT.

Example:

SELECT user_id FROM orders WHERE amount > 500
UNION
SELECT user_id FROM orders WHERE status = 'shipped';


Advanced Query Optimization:

Query Execution Plans: Understand and analyze query execution plans to optimize SQL queries.

Batch Processing: Use batch inserts/updates to reduce round-trip database calls.

Database Caching: Use caching mechanisms (like @Cacheable in Spring Boot) to improve performance.

Example:

@Cacheable("users")
public List<User> findUsers() {
    return userRepository.findAll();
}


Database Connection Pooling:

Use HikariCP (Spring Boot’s default connection pool) for optimal database connection management.

Fine-tune database connection pool settings like max idle connections, max pool size, etc.

Example:

spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=10


Database Sharding & Partitioning:

Sharding: Divide your data across multiple databases or servers to distribute the load.

Partitioning: Divide large tables into smaller, more manageable parts (partitions).

Spring Boot & Database Optimization:

JPA Performance Tuning:

Lazy Loading vs. Eager Loading:

Lazy loading: Load related data only when accessed.

Eager loading: Load related data immediately, which can be less efficient for large datasets.

Example of Lazy Loading:

@OneToMany(fetch = FetchType.LAZY)
private List<Order> orders;


Batch Processing and Transactions:

Use Spring Batch for batch processing of large data sets, such as loading, processing, and writing to databases.

Group database operations into transactions to ensure consistency and reduce overhead.

Example:

@Transactional
public void updateUserOrders(User user) {
    // Logic to update user orders
}


Database Migrations:

Use Flyway or Liquibase to manage database schema changes across environments (development, staging, production).

Example with Flyway:

spring:
  flyway:
    enabled: true
    locations: classpath:db/migration

Final Advanced Optimizations:

Database Replication: Set up master-slave replication to scale reads.

Data Compression: Compress large tables to save space (with zlib or other compression techniques).

Parallel Query Execution: Use parallel execution strategies for long-running queries.

Database Tuning: Adjust database configurations like buffer pool size, query cache, etc., to improve performance.

Recap of Technologies to Use with Spring Boot:

JPA / Hibernate for ORM-based queries.

JdbcTemplate for lower-level SQL queries.

Spring Data JPA for repositories with query methods.

Spring Batch for efficient batch processing.



-------------------------------------------------------------------------------------------------------------------------
Day 1 – SQL Basics

SELECT statement

DISTINCT

WHERE clause

AND / OR / NOT

ORDER BY

LIMIT / TOP

Day 2 – Aggregate Functions

COUNT

SUM

AVG

MIN / MAX

GROUP BY

HAVING

Day 3 – Joins (Basics)

INNER JOIN

LEFT JOIN

RIGHT JOIN

Join conditions

Joining multiple tables

Day 4 – Joins (Advanced)

FULL OUTER JOIN

SELF JOIN

CROSS JOIN

Joins with GROUP BY

Filtering after JOIN

Day 5 – Subqueries

Subquery in WHERE

Subquery in SELECT

Subquery in FROM

Correlated subqueries

EXISTS / NOT EXISTS

Day 6 – Set Operations

UNION

UNION ALL

INTERSECT

EXCEPT / MINUS

Day 7 – Functions

String functions

Date & time functions

Numeric functions

NULL handling (IS NULL, COALESCE)

CASE WHEN

Day 8 – Indexing & Optimization

Index basics

Single-column index

Composite index

EXPLAIN / EXPLAIN ANALYZE

Query optimization basics

Day 9 – Transactions & Constraints

Transactions (BEGIN, COMMIT, ROLLBACK)

Primary key

Foreign key

Unique constraint

Check constraint

Day 10 – Advanced SQL

Views

Window functions (ROW_NUMBER, RANK, DENSE_RANK)

CTE (WITH clause)

Pagination queries

Performance tuning concepts


Day 101----------------------------------------------------

USE classicmodels;

SELECT customerName
FROM customers;


SELECT DISTINCT customerName
FROM customers;

SELECT *
FROM customers
WHERE customerName="Signal Gift Stores";


SELECT customerName
FROM customers
WHERE NOT customerName="Signal Gift Stores";



SELECT *
FROM customers
WHERE customerName="Signal Gift Stores" AND customerName="Australian Collectors, Co.";

SELECT *
FROM customers
WHERE customerName="Signal Gift Stores" OR customerName="Australian Collectors, Co.";


SELECT *
FROM customers
ORDER BY customerName;

SELECT *
FROM customers
ORDER BY customerName DESC;

SELECT *
FROM customers
ORDER BY customerName ASC;

SELECT *
FROM customers
LIMIT 5;

SELECT *
FROM customers
LIMIT 5 OFFSET 10;

SELECT TOP 5 *
FROM customers;

SELECT DISTINCT name
FROM customers
WHERE customerName = 'Signal Gift Stores' AND buyPrice > 60
LIMIT 5;


Day 102---------------------------------------------------------------------

USE classicmodels;

SELECT COUNT(*) FROM customers;

SELECT COUNT(*) FROM customers WHERE country="USA";

SELECT SUM(creditLimit) FROM customers ;

SELECT AVG(creditLimit) FROM customers ;

SELECT MIN(creditLimit), MAX(creditLimit)FROM customers;


SELECT customerNumber, COUNT(*) AS customers
FROM customers
GROUP BY customerNumber;


SELECT customerNumber
FROM orders
GROUP BY customerNumber
HAVING COUNT(*) > 5;


SELECT customerNumber, COUNT(*) AS total_orders
FROM orders
WHERE orderDate >= '2003-04-28'
GROUP BY customerNumber
HAVING COUNT(*) > 5;


 Day 103 ------------------------------------------------------------------------------------------------


  */


