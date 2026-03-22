package com.example.Databases_System_Design_013.url.serviceImpl;

import com.example.Databases_System_Design_013.url.service.RateLimiterService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimiterServiceImpl implements RateLimiterService {
    private final Map<String, Long> requestCounts = new ConcurrentHashMap<>();
    private final long LIMIT = 5; // max requests
    private final long WINDOW = 60_000; // 1 minute

    private final Map<String, Long> timestamps = new ConcurrentHashMap<>();

    public boolean allowRequest(String ip) {
        long currentTime = System.currentTimeMillis();

        timestamps.putIfAbsent(ip, currentTime);
        requestCounts.putIfAbsent(ip, 0L);

        if (currentTime - timestamps.get(ip) > WINDOW) {
            timestamps.put(ip, currentTime);
            requestCounts.put(ip, 0L);
        }

        long count = requestCounts.get(ip);

        if (count >= LIMIT) {
            return false;
        }

        requestCounts.put(ip, count + 1);
        return true;
    }

}
