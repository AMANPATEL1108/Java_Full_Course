package com.example.Databases_System_Design_013.url.service;

public interface RateLimiterService {
    public boolean allowRequest(String ip);
}
