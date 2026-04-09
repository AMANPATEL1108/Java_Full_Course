package com.example.Cloud_Devops_02.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_test")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;
}