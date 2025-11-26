package com.example.springBoot_01.api.dayeighty;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // We can define custom queries here, if needed
}
