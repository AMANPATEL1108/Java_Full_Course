package com.example.Databases_System_Design_06.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {  // <--- implement Serializable

    private Long id;
    private String name;
}
