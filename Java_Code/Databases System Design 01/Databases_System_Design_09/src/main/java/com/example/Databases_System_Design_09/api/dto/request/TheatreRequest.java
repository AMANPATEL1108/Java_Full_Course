package com.example.Databases_System_Design_09.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TheatreRequest {

    @NotBlank(message = "Theatre name is required")
    private String name;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Address is required")
    private String address;
}