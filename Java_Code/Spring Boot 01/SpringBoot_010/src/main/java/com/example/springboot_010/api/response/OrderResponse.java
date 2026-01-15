package com.example.springboot_010.api.response;

import java.time.LocalDateTime;

public class OrderResponse {

    private String message;
    private String userName;
    private LocalDateTime orderDate;

    public OrderResponse(String message, String userName) {
        this.message = message;
        this.userName = userName;
        this.orderDate = LocalDateTime.now();
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
