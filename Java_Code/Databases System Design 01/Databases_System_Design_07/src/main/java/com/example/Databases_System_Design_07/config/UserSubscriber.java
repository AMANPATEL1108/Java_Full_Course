package com.example.Databases_System_Design_07.config;

import org.springframework.stereotype.Component;

@Component
public class UserSubscriber {

    public void handleMessage(String message) {
        System.out.println("📥 Message received: " + message);
    }
}
